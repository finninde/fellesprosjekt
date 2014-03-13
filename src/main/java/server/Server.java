package server;

/**
 * Created by espen on 12.03.14.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server
{
    private HashMap<String, Worker> clients;
    private final static Main2000 listener = new Main2000();


    public Server() throws IOException {
        printString("Server har startet!");
        clients = new HashMap<String, Worker>();
        ServerSocket welcomeSocket = new ServerSocket(6789);
        //System.out.println("Server up and running");

        while(true)
        {

            Socket connectionSocket = welcomeSocket.accept();
            if (connectionSocket != null)
            {
                new LoginHandler(connectionSocket, this);
            }
        }
        //System.out.println("Server shutting down");
    }
    public void printString(String s){
        listener.printString(s);
    }
    public boolean usernameExists(String username) {

        return clients.containsKey(username) ? true : false;

    }
    public void addWorker(String username, Worker worker) {
        clients.put(username, worker);
    }
}