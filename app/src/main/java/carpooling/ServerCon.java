package carpooling;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerCon
{
    public static final String TYPE_AUTH = "AUTH";
    public static final String TYPE_REGISTER = "REGISTER";
    private static final String ip = "89.90.8.243";
    private static final int port = 1839;
    private static final String SEP_MARKER = ":";
    private static final String END_MARKER = "\n";
    private Socket sock;
    private BufferedReader in;
    private PrintWriter out;
    private Debug dbg;

    ServerCon()
    {
        try
        {
            this.dbg = new Debug("ServerCon");
            this.sock = new Socket(this.ip, this.port);
            this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            this.out = new PrintWriter(sock.getOutputStream(), true);
        }
        catch(IOException ioe)
        {
            dbg.error("IOException caught: '" + ioe.getMessage() +"'");
        }

    }

    public void send(String[] data)
    {
        String toSend = null;

        toSend = TextUtils.join(SEP_MARKER, data);
        toSend += END_MARKER;
        out.printf("%s", toSend);
    }

    public String[] receive()
    {
        String data;

        try
        {
            data = in.readLine();
            if (data.isEmpty())
                return null;
            return data.split(":");
        }
        catch (IOException ioe)
        {
            dbg.error("IOException caught: '" + ioe.getMessage() +"'");
        }

        return null;
    }

    public void closeCon()
    {
        try
        {
            dbg.warning("Closing streams & connection to " + sock.getRemoteSocketAddress());
            sock.close();
            out.close();
            in.close();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace(System.err);
        }
    }
}