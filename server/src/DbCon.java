/**
 * Created by yassine on 3/28/17.
 */

import java.sql.*;

public class DbCon
{
    /* Database config */
    static final String jdbcDriver = "com.mysql.jdbc.Driver";
    static final String dbUrl = "jdbc:mysql://localhost/proj_db";
    static final String user = "proj";
    static final String password = "passwd";
    private Connection con = null;
    private Debug dbg;

    public DbCon()
    {
        dbg = new Debug("DBCON");

        try
        {
            /* Register driver */
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(dbUrl, user, password);
        } catch (ClassNotFoundException cnfe)
        {
            dbg.error("ClassNotFoundException caught: '" + cnfe.getMessage() +"'");
        } catch (SQLException se)
        {
            dbg.error("SQLException caught: '" + se.getMessage() + "'");
        }
    }

    public Connection getCon()
    {
        return con;
    }
}
