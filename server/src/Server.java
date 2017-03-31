/**
 * Created by yassine on 3/16/17.
 */

import java.net.*;
import java.io.*;

public class Server
{
    protected static Debug dbg;
    private static int PORT = 1839;

    public static void main(String[] args)
    {
        ServerSocket listener = null;
        Socket sock = null;

        dbg = new Debug("SERVER");
        dbg.info("Starting server on port " + PORT + " ...");

        try
        {
            listener = new ServerSocket(PORT, 8);
            dbg.info("Server started, waiting for clients ...");
        }
        catch (IOException ioe)
        {
            dbg.error("IOException caught: '" + ioe.getMessage() + "'");
        }

        while (true)
        {
            try
            {
                sock = listener.accept();
                new Thread(new Worker(sock)).start();
            }
            catch (IOException ioe)
            {
                dbg.error("IOException caught: '" + ioe.getMessage() + "'");
            }
        }
    }
}
