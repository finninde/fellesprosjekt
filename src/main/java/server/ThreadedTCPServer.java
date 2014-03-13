package server;

/**
 * Created by espen on 12.03.14.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadedTCPServer
{
    private ArrayList<ClientHandler> clients;
    private final static Main2000 listener = new Main2000();


    public ThreadedTCPServer() throws IOException {
        printString("Server har startet!");
        clients = new ArrayList<ClientHandler>();
        ServerSocket welcomeSocket = new ServerSocket(6789);
        //System.out.println("Server up and running");

        while(true)
        {

            Socket connectionSocket = welcomeSocket.accept();
            if (connectionSocket != null)
            {
                ClientHandler client = new ClientHandler(connectionSocket, this);
                this.clients.add(client);
            }
        }
        //System.out.println("Server shutting down");
    }
    public void printString(String s){
        listener.printString(s);
    }
}