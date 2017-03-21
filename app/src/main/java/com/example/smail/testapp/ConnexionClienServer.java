package com.example.smail.testapp;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Created by smail on 05/03/17.
 */

public class ConnexionClienServer {
    Socket    ClientSocket;
    Thread m_objectThreadClient;
    String response;


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

    };


}


