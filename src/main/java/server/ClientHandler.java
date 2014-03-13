package server;
import java.io.*;
import java.net.*;
/**
 * Created by espen on 12.03.14.
 */
public class ClientHandler extends Thread {

    private Socket connectionSocket;
    private String clientSentence;
    private String capitalizedSentence;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;
    private ThreadedTCPServer server;

    public ClientHandler(Socket c, ThreadedTCPServer server) throws IOException
    {
        connectionSocket = c;
        this.server = server;
        this.start();
    }

    public void run()
    {
        try
        {
            while(true) {
                inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                clientSentence = inFromClient.readLine();
                this.server.printString(clientSentence);
                capitalizedSentence = clientSentence.toUpperCase() + '\n';
                outToClient.writeBytes(capitalizedSentence);
            }
        }
        catch(IOException e)
        {
           this.server.printString("Errore: " + e);
        }
    }
}
