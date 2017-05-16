import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by yassine on 5/15/17.
 */

public class PathMatching
{
    /* Max distance for a matching (kilometer) */
    private static double MAX_DISTANCE = 1.0;
    public static String TYPE_DRIVER = "T_CONDUCTEUR";
    public static String TYPE_PASSENGER = "T_PASSAGER";

    public static boolean match(Coordinate c, ArrayList<Coordinate> cList)
    {
        for(Coordinate c1: cList)
            if (c.distance(c1) <= MAX_DISTANCE)
                return true;
        return false;
    }

    private static void addMatch(String pseudoDriver, String pseudoPassenger, int id)
    {
        try
        {
            PreparedStatement p = Worker.db.getCon().prepareStatement("INSERT INTO Matchs VALUES(?, ?, ?)");
            p.setString(1, pseudoDriver);
            p.setString(2, pseudoPassenger);
            p.setInt(3, id);
            p.executeUpdate();
        }
        catch(SQLException se)
        {
        }
    }

    /* passenger asking for a match */
    public static void matchPassenger(String pseudo, ArrayList<Coordinate> coordinates)
    {
        try
        {
            PreparedStatement p = Worker.db.getCon().
                    prepareStatement("SELECT id, pseudo, liste_points FROM " +
                            "Itineraire, Effectuer_itineraire WHERE type = ? AND " +
                            "Itineraire.id = Effectuer_itineraire.id_itineraire");
            p.setString(1, TYPE_DRIVER);
            ResultSet res = p.executeQuery();
            res.next();

            do
            {
                int id = res.getInt("id");
                String driverPseudo = res.getString("pseudo");
                ArrayList<Coordinate> cList = Coordinate.str2coordinates(res.getString("liste_points"));

                if (match(coordinates.get(0), cList))
                    addMatch(driverPseudo, pseudo, id);

                res.next();
            } while (res.next());

        }
        catch(SQLException se)
        {
        }
    }

    /* driver asking for a match */
    public static void matchDriver(String pseudo, ArrayList<Coordinate> coordinates)
    {
        try
        {
            PreparedStatement p = Worker.db.getCon().
                    prepareStatement("SELECT id, pseudo, liste_points FROM " +
                            "Itineraire, Effectuer_itineraire WHERE type = ? AND " +
                            "Itineraire.id = Effectuer_itineraire.id_itineraire");
            p.setString(1, TYPE_PASSENGER);
            ResultSet res = p.executeQuery();
            res.next();

            do
            {
                int id = res.getInt("id");
                String passengerPseudo = res.getString("pseudo");
                Coordinate c = Coordinate.str2coordinates(res.getString("liste_points")).get(0);

                if (match(c, coordinates))
                    addMatch(pseudo, passengerPseudo, id);

            } while (res.next());

        }
        catch(SQLException se)
        {
        }
    }
}
