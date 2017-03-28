package carpooling;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnexionClientServer
{
    private Socket ClientSocket;
    private Thread m_objectThreadClient;
    private String response;


    public ConnexionClientServer()
    {
        m_objectThreadClient = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    ClientSocket = new Socket("89.90.8.243", 1337);

                    OutputStream oss = new ObjectOutputStream(ClientSocket.getOutputStream());
                    PrintWriter writer = new PrintWriter(oss);
                    writer.print("Init com. server");
                    writer.flush();

                    InputStream ois = new ObjectInputStream(ClientSocket.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(ois));
                    response = reader.readLine();
                    Log.d("d_SERVER", "message recu = " + response);


                    ClientSocket.close();

                } catch (Exception e)
                {
                    e.printStackTrace();
                    Log.d("d_SERVER", "COM error : " + e.getMessage());
                }
            }
        });
        m_objectThreadClient.start();

    }
}


