package server;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by espen on 13.03.14.
 */
public class Worker implements ConnectionListener{

    private String username;
    private Socket connection;
    private Server server;
    private ObjectOutputStream toClient;

    public Worker(Server server, Socket connection, String username) {
        this.username = username;
        this.connection = connection;
        this.server = server;
        try {
            toClient = new ObjectOutputStream(connection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Receiver(this,connection);
        server.addWorker(username,this);
        welcomeMessage();

    }

    public void sendJSON(JSONObject json) {
        try {
            toClient.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void welcomeMessage() {
        JSONObject obj = new JSONObject();
        obj.put("response", "login");
        obj.put("success", true);
        sendJSON(obj);

    }

    @Override
    public void recievedMessage(JSONObject obj) {
        //TODO not yet implemented... This method should handle all possible requests from the user...
        System.out.println(obj);
    }
}
