/**
 * Created by yassine on 3/17/17.
 */

import java.net.*;
import java.io.*;
import java.sql.*;

public class Worker implements Runnable
{
    private Socket sock;
    private DbCon db;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Debug dbg;

    /* defining the 'protocol' */
    private static final String SEP_MARKER = ":";
    private static final String END_MARKER = "\n";
    /* request types */
    private static final String TYPE_AUTH = "AUTH";
    private static final String TYPE_REGISTER = "REGISTER";
    private static final String TYPE_UNKNOWN = "TYPE_UNKNOWN";

    public Worker(Socket sock)
    {
        this.sock = sock;
        db = new DbCon();
        dbg = new Debug("WORKER");
    }

    public void reqRegister(String pseudo, String hash, String mail, String tel)
    {
        String mailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        /* always check input */
        if (pseudo.length() > 32 || hash.length() != 64 || tel.length() != 10 || !mail.matches(mailRegex))
        {
            send(new String[] {"REGISTER", "0"});
            return;
        }

        try
        {
            PreparedStatement prepStmt = db.getCon()
                    .prepareStatement("INSERT INTO Utilisateur VALUES(?, ?, ?, ?)");
            prepStmt.setString(1, pseudo);
            prepStmt.setString(2, hash);
            prepStmt.setString(3, mail);
            prepStmt.setString(4, tel);
        }
        catch (SQLException se)
        {
            dbg.error("SQLException caught: '" + se.getMessage() + "'");
            send(new String[] {"REGISTER", "0"});
            return;
        }

        send(new String[] {"REGISTER", "1"});
    }

    public void reqAuth(String pseudo, String hash)
    {
        try
        {
            dbg.info("Processing AUTH request for user '" + pseudo + "' from '" + sock.getRemoteSocketAddress() + "' ...");
            PreparedStatement prepStmt = db.getCon()
                    .prepareStatement("SELECT COUNT(*) AS c FROM Utilisateur" +
                            " WHERE pseudo = ? AND hash = ?");
            prepStmt.setString(1, pseudo);
            prepStmt.setString(2, hash);
            ResultSet res = prepStmt.executeQuery();

            res.next(); /* get to the first row */
            if (res.getInt("c") == 1)
                send(new String[] {"AUTH", "1"});
            else
                send(new String[] {"AUTH", "0"});
        }
        catch (SQLException se)
        {
            dbg.error("SQLException caught: '" + se.getMessage() + "'");
            send(new String[] {"AUTH", "0"});
        }
    }

    private void closeCon()
    {
        try
        {
            dbg.warning("Closing streams & connection to '" + sock.getRemoteSocketAddress() + "'");
            sock.close();
            out.close();
            in.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace(System.err);
        }
    }

    private void send(String[] data)
    {
        String toSend = null;

        toSend = String.join(SEP_MARKER, data);
        dbg.info("Sending response to '" + sock.getRemoteSocketAddress() + "' : '" + toSend + "' ...");
        toSend += END_MARKER;
        out.printf("%s", toSend);
    }

    public void run()
    {
        Args args = null;
        String type;

        dbg.info("Incoming connection from '" + sock.getRemoteSocketAddress() + "'");

        try
        {
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);
        }
        catch (IOException ioe)
        {
            dbg.error("IOException caught: '" + ioe.getMessage() + "'");
        }

        /* requete: champs separes par ':' marqueur de fin ='\n'
        *  example: AUTH:login:password\n
        */
        args = new Args(in, SEP_MARKER);
        args.getArgs();

        if (args.size() == 0)
        {
            dbg.warning("Empty request");
            closeCon();
            return;
        }

        dbg.info("Received query from '" + sock.getRemoteSocketAddress() + "': " + args);

            /* request type */
        type = args.get(0);

        if (type.equals(TYPE_AUTH))
        {
            if (args.size() != 3)
                return;

            reqAuth(args.get(1), args.get(2));
        }
        else if (type.equals(TYPE_REGISTER))
        {
            if (args.size() != 5)
                return;

            reqRegister(args.get(1), args.get(2), args.get(3), args.get(4));
        }
        else
        {
            dbg.warning("Unknown request type '" + type + "'");
            send(new String[]{"ERROR", TYPE_UNKNOWN});
        }

        closeCon();
    }
}