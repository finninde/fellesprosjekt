package server;

import database.repository.UserRepository;
import helperclasses.User;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by espen on 12.03.14.
 */
public class LoginHandler implements ConnectionListener{

    private Socket connectionSocket;

    private ObjectOutputStream toClient;
    private Server server;
    public JSONObject test;

    public LoginHandler(Socket c, Server server) throws IOException
    {
        connectionSocket = c;
        this.server = server;
        try {
            toClient = new ObjectOutputStream(connectionSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Receiver(this,connectionSocket);

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
        UserRepository ur = new UserRepository();
        User user = ur.getUser(username);
        if(!user.getPassword().equals(password)) {
            sendError("Wrong password");
        }


        new Worker(server,connectionSocket, username);


    }
    public void sendError(String errorMessage) {
        JSONObject json = new JSONObject();
        json.put("response", "login");
        json.put("success", false);
        json.put("error", errorMessage);
        sendJSON(json);

    }
}
