/**
 * Created by yassine on 3/17/17.
 */

import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Worker implements Runnable
{
    private Socket sock;
    private DbCon db;
    private BufferedReader in = null;
    private PrintWriter out = null;
    protected static Debug dbg;

    public Worker(Socket sock)
    {
        this.sock = sock;
        db = new DbCon();
        dbg = new Debug("WORKER");
    }

    public void reqRegister(String pseudo, String hash, String mail, String tel)
    {
        /* TODO: check values before insert */
        try
        {
            PreparedStatement prepStmt = db.getCon()
                    .prepareStatement("INSERT INTO Utilisateur VALUES(?, ?, ?, ?)");
            prepStmt.setString(1, pseudo);
            prepStmt.setString(2, hash);
            prepStmt.setString(3, mail);
            prepStmt.setString(4, tel);
            out.printf("REGISTER\r\n1"); /* REGISTER successfull */
        }
        catch (SQLException se)
        {
            dbg.error("SQLException caught: '" + se.getMessage() + "'");
            out.printf("REGISTER\r\n0"); /* REGISTER failure */
        }
    }

    public void reqAuth(String pseudo, String hash)
    {
        try
        {
            PreparedStatement prepStmt = db.getCon()
                    .prepareStatement("SELECT COUNT(*) AS c FROM Utilisateur" +
                            " WHERE pseudo = ? AND hash = ?");
            prepStmt.setString(1, pseudo);
            prepStmt.setString(2, hash);
            ResultSet res = prepStmt.executeQuery();

            if (res.getInt("c") == 1)
                out.printf("AUTH\r\n1"); /* AUTH successfull */
            else
                out.printf("AUTH\r\n0"); /* AUTH failure */
        }
        catch (SQLException se)
        {
            dbg.error("SQLException caught: '" + se.getMessage() + "'");
        }
    }

    private void closeCon()
    {
        try
        {
            dbg.warning("Closing connection " + sock.getRemoteSocketAddress());
            sock.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace(System.err);
        }
    }

    private ArrayList<String> getReqArgs(BufferedReader buf)
    {
        ArrayList<String> args = new ArrayList<>();
        String line;

        try
        {
            while ((line = buf.readLine()) != null)
                args.add(line);
        }
        catch (IOException ioe)
        {
            dbg.error("IOException caught: '" + ioe.getMessage() + "'");
        }

        return args;
    }

    public void run()
    {
        ArrayList<String> args = null;
        String type;

        dbg.info("Incoming connection from '" + sock.getRemoteSocketAddress() + "'");

        try
        {
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream());
        }
        catch (IOException ioe)
        {
            dbg.error("IOException caught: '" + ioe.getMessage() + "'");
        }


        args = getReqArgs(in);
        dbg.info("Received query from '" + sock.getRemoteSocketAddress() + "': " + args);
        if (args == null || args.isEmpty())
            closeCon();

            /* Request type */
        type = args.get(0).toLowerCase();

        if (type.equals("auth"))
        {
            if (args.size() != 3)
                return;

            reqAuth(args.get(1), args.get(2));
        }
        else if (type.equals("register"))
        {
            if (args.size() != 5)
                return;

            reqRegister(args.get(1), args.get(2), args.get(3), args.get(4));
        }

        closeCon();
    }
}