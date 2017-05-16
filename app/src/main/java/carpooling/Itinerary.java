package carpooling;


import java.util.ArrayList;

public class Itinerary
{
    public static boolean sendItininerariesToServer(String user, int userType, ArrayList liste)
    {
        liste.add(0, ServerCon.TYPE_SAVE_WP);
        liste.add(1, user);
        liste.add(2, userType);
        //TODO add user identification

        String[] data = new String[liste.size()];
        liste.toArray(data);

        ServerCon con = new ServerCon();

        con.send(data);
        String[] res = con.receive();

        con.closeCon();

        if (res == null || res.length < 2)
            return false;

        //TODO handle answer

        return true;
    }

    public static String validateMatchBetweenUsers(String user, String matchedUser)
    {
        ServerCon con = new ServerCon();
        con.send(new String[]{ServerCon.TYPE_VALIDATE_MATCH, user, matchedUser});
        String[] res = con.receive();
        con.closeCon();

        return res[1];
    }


}
