package server;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by espen on 12.03.14.
 */
public class LoginHandler extends Thread implements ConnectionListener{

    private Socket connectionSocket;
    private boolean running;
    private ObjectOutputStream toClient;
    private Server server;
    public JSONObject test;
    private Receiver receiver;

    public LoginHandler(Socket c, Server server)
    {
        System.out.println("login handler created.");
        connectionSocket = c;
        this.server = server;
        this.start();

    }

    public void run() {
        running = true;
        try {
            toClient = new ObjectOutputStream(connectionSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        receiver = new Receiver(this,connectionSocket);
        while(running) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("LoginHandler deleted...");
    }

    public void sendJSON(JSONObject json) {
        try {
            toClient.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void recievedMessage(JSONObject obj) {
        System.out.println(obj);
        String requestType = (String) obj.get("request");
        if(!requestType.equals("login")) {
            sendError("unknown request");
            return;
        }
        String username = null;
        String password = null;
        try {
            username = (String) obj.get("username");
            password = (String) obj.get("password");
        } catch(Exception e) {
            sendError("Couldn't find username or password");
        }
        if(server.usernameExists(username)) {
            sendError("Username already logged in!");
            return;
        }
        if(username.equals("espen")) {
            System.out.println("Success!");
        }

//        UserRepository ur = new UserRepository();
//        User user = ur.getUser(username);
//        if(!user.getPassword().equals(password)) {
//            sendError("Wrong password");
//        }


        new Worker(server,connectionSocket, username, receiver,toClient);
        running = false;
    }

    @Override
    public boolean running() {
        return running;
    }

    @Override
    public void disconnect() {
        try {
            running = false;
            toClient.close();
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendError(String errorMessage) {
        JSONObject json = new JSONObject();
        json.put("response", "login");
        json.put("success", false);
        json.put("error", errorMessage);
        sendJSON(json);

    }
}
