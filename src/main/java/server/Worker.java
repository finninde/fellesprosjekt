package server;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by espen on 13.03.14.
 */
public class Worker extends Thread implements ConnectionListener{

    private String username;
    private Socket connection;
    private Server server;
    private ObjectOutputStream toClient;
    private boolean running;

    public Worker(Server server, Socket connection, String username, Receiver receiver,ObjectOutputStream toClient) {
        running = true;
        System.out.println("worker created");
        receiver.changeListener(this);
        this.username = username;
        this.connection = connection;
        this.server = server;
        this.toClient = toClient;
        this.start();
    }

    public void run() {
        server.addWorker(username,this);
        welcomeMessage();
        while(running) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("worker dyying...");
    }

    public void sendJSON(JSONObject json) {
        try {
            System.out.println("Sending welcome message");
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
        if(obj.get("request").equals("logout")) {
            server.removeWorker(username);
            JSONObject json = new JSONObject();
            json.put("response", "logout");
            json.put("success", true);
            sendJSON(json);
        }
    }

    @Override
    public boolean running() {
        return running;
    }

    @Override
    public void disconnect() {
        server.removeWorker(username);
        if(toClient != null) {
            try {
                running = false;
                toClient.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
