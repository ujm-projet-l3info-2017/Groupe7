package carpooling;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnexionClienServer {
    private Socket    ClientSocket;
    private Thread m_objectThreadClient;
    private String response;


    public ConnexionClienServer(){
        m_objectThreadClient = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ClientSocket = new Socket( "89.90.8.243", 1337);

                    OutputStream oss = new ObjectOutputStream(ClientSocket.getOutputStream());
                    PrintWriter writer = new PrintWriter(oss);
                    writer.print("Hey server");
                    writer.flush();

                    InputStream ois = new ObjectInputStream(ClientSocket.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(ois));
                    response = reader.readLine();
                    System.out.println("le messages reçu"+response);

                    ClientSocket.close();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.print("erreur de conexion");
                }
            }
        });
        m_objectThreadClient.start();

    }
}


