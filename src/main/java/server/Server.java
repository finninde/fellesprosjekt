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
    private boolean running;

    public Server(int port) {
        clients = new HashMap<String, Worker>();
        ServerSocket welcomeSocket = null;
        try {
            welcomeSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server up and running");
        running = true;

        while(running)
        {

            Socket connectionSocket = null;
            try {
                connectionSocket = welcomeSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (connectionSocket != null)
            {
                new LoginHandler(connectionSocket, this);
            }
        }
        System.out.println("Server shutting down");
    }
    public static void main(String args[]) {
        Server server = new Server(6789);
    }

    public boolean usernameExists(String username) {

        return clients.containsKey(username) ? true : false;

    }
    public void addWorker(String username, Worker worker) {
        clients.put(username, worker);
    }
    public void removeWorker(String username) {
        if(clients.containsKey(username)) {
            clients.remove(username);
        }
    }
    public void shutDown() {
        running = false;
    }
}