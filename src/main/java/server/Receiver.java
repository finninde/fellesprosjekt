package server;

import org.json.simple.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by espen on 13.03.14.
 */
public class Receiver extends Thread{

    private ConnectionListener listener;
    private Socket connection;
    private ObjectInputStream in;

    public Receiver(ConnectionListener listener, Socket connection) {
        setDaemon(false);
        System.out.println("reciever created!");
        this.listener = listener;
        this.connection = connection;
        try {
            in = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.start();
    }
    public void run() {
        while(true) {
            try {
                JSONObject obj = (JSONObject)in.readObject();
                if(!listener.running()) {
                    break;
                }
//                if(obj == null) {
//                    continue;
//                }
            listener.recievedMessage(obj);
        } catch(SocketException e ) {
            System.out.println("receiver stopped working");
                break;
        } catch (EOFException e) {
            System.out.println("Client disconnected...");
            break;
        } catch (IOException e) {
            e.printStackTrace();
                break;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
                break;
        } catch(NullPointerException e) {
                continue;
            }
            catch (Exception e) {
                System.out.println("got unknownException");
                e.printStackTrace();
                break;
            }

        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listener.disconnect();

        System.out.println("reciever dyying. ");
    }
    public void changeListener(ConnectionListener cl) {
        this.listener = cl;
        System.out.println("Reciever changed listener");
    }
}
