package carpooling;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnexionClientServer
{
    private final int connection_time_out = 5000;
    //private final String host_ip = "89.90.8.243";
    private final String host_ip = "127.0.0.1";
    private final int port_num = 1839;

    private Thread m_objectThreadClient;

    public ConnexionClientServer()
    {
        m_objectThreadClient = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Socket clientSocket = new Socket();

                    clientSocket.connect(new InetSocketAddress(host_ip, port_num), connection_time_out);
                    //clientSocket = new Socket("89.90.8.243", 1337);
                    String response;


                    OutputStream oss = new ObjectOutputStream(clientSocket.getOutputStream());
                    PrintWriter writer = new PrintWriter(oss);

                    writer.print("Init com. server");
                    writer.flush();

                    InputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(ois));
                    response = reader.readLine();
                    Log.d("d_SERVER", "Message recu = " + response);


                    clientSocket.close();

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


