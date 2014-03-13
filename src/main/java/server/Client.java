package server;

import java.io.*;
import java.net.Socket;

/**
 * Created by espen on 12.03.14.
 */
public class Client {
    public static void main(String args[]) throws Exception
    {
        String sentence;
        String modifiedSentence;

        Socket clientSocket = new Socket("localhost", 6789);

        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader inFromUser = new BufferedReader(input);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));



        sentence = inFromUser.readLine();
        System.out.println("0");
        outToServer.writeBytes(sentence);
        System.out.println("0.1");
        try {
            while (!sentence.equals("exit")) {
                System.out.println(sentence);
                sentence = inFromUser.readLine();
                System.out.println("1");
                outToServer.writeBytes(sentence + '\n');
                System.out.println("2");
                modifiedSentence = inFromServer.readLine();
                System.out.println("3");
                System.out.println(modifiedSentence);

            }
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
        clientSocket.close();
    }
}
