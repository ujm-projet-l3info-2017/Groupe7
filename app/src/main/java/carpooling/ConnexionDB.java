package carpooling;

import android.util.Log;

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
            Log.d("d_DB", "CONNECTION DB success.");
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.d("d_DB", "CONNECTION DB failed.");
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
            Log.d("d_DB", "ReqModif(query) SQL exception : " + e.getMessage());
        }

        return -1;
    }
}