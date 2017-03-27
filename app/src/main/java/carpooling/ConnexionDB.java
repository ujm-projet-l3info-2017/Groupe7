package carpooling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class ConnexionDB
{

    private Statement st;
    private Connection con;

    public ConnexionDB() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://161.3.108.33:3306/db_proj", "tm06288p", "adoula23");
            System.out.println("Connexion reussi");
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("connection failed");
        }


    }

    protected ResultSet reqSelect(String query) throws SQLException
    {

        return con.createStatement().executeQuery(query);
    }

    protected int reqModif(String query)
    {
        PreparedStatement pstmt;

        try
        {
            pstmt = con.prepareStatement(query);
            return pstmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return -1;
    }
}