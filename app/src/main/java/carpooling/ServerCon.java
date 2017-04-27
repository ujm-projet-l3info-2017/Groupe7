package carpooling;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerCon
{
    public static final String TYPE_AUTH = "AUTH";
    public static final String TYPE_REGISTER = "REGISTER";

    /* "To access your PC localhost from Android emulator, use 10.0.2.2 instead of 127.0.0.1.
        localhost or 127.0.0.1 refers to the emulated device itself, not the host the emulator is running on."
        => http://stackoverflow.com/questions/18341652/connect-failed-econnrefused
        sounds obvious after all ...
    */
    // private static final String ip = "10.0.2.2";
    private static final String ip = "89.90.8.243";
    private static final int port = 1839;
    private static final String SEP_MARKER = ":";
    private static final String END_MARKER = "\n";
    private Socket sock;
    private BufferedReader in;
    private PrintWriter out;

    public ServerCon()
    {
        try
        {
            Log.d("ServerCon", "Connecting to server at " + this.ip + ":" + this.port + " ...");
            this.sock = new Socket(this.ip, this.port);
            this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            this.out = new PrintWriter(sock.getOutputStream(), true);
        }
        catch(IOException ioe)
        {
            Log.e("IOException caught: '", "" + ioe.getMessage());
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
            Log.e("IOException caught: '", "" + ioe.getMessage());
        }

        return null;
    }

    public void closeCon()
    {
        try
        {
            Log.w("Closing streams & con", "" + sock.getRemoteSocketAddress());
            sock.close();
            out.close();
            in.close();
        }
        catch(IOException ioe)
        {
            Log.e("IOException caught: '", "" + ioe.getMessage());
        }
    }
}