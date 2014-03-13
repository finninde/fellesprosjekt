package server;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by espen on 13.03.14.
 */
public class Receiver extends Thread{

    private ConnectionListener listener;
    private Socket connection;
    public Receiver(ConnectionListener listener, Socket connection) {
        setDaemon(false);
        this.listener = listener;
        this.connection = connection;

    }
    public void run() {
        try {
            ObjectInputStream inFromClient = new ObjectInputStream(connection.getInputStream());
            JSONObject obj = (JSONObject)inFromClient.readObject();
            new Receiver(listener, connection);
            listener.recievedMessage(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
