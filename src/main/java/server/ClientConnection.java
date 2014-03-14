package server;

import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import helperclasses.Request;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;


/**
 * Created by espen on 12.03.14.
 */
public class ClientConnection extends Thread implements ConnectionListener{
    private boolean running;
    private HashMap<Integer, Object> incomingObjects;
    private int count;
    private ObjectOutputStream toServer;
    private Socket clientSocket;

    public ClientConnection(String address, int port){
        clientSocket = null;
        try {
            clientSocket = new Socket(address, port);
            toServer = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        running = true;
        this.start();
        System.out.println("Done creating client connection");
    }
    public void run() {
        incomingObjects = new HashMap<Integer, Object>();
        new Receiver(this,clientSocket);
        while(true) {
            try {
                count += 1;
                sleep(100);
                if(count == 10) { logout(); }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void logout() {
        System.out.println("about to logout");
        JSONObject json = new JSONObject();
        json.put("request", "logout");
        send(json);
    }
    public static void main(String args[]) {
        System.out.println("wallabaya!");
        ClientConnection client = new ClientConnection("78.91.21.40", 6789);
        JSONObject json = new JSONObject();
        json.put("request","login");
        json.put("username", "espen");
        json.put("password", "1234");
        client.send(json);

    }


    @Override
    public void recievedMessage(JSONObject obj) {
        System.out.println(obj);
        String response = (String) obj.get("response");
        if(response.equals("logout")) {
            if((boolean)obj.get("success")) {
                System.out.println("disconnecting....");
                disconnect();
            }
        }
    }
    public Object waitForObject(Integer key) {
        DateTime timer = new DateTime();
        while(true) {
            if(incomingObjects.containsKey(key)){
                return incomingObjects.get(key);
            }
            try {
                timer.wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean running() {
        return running;
    }

    @Override
    public void disconnect() {
        System.out.println("Sorry, server shutted down, or error");
        running = false;
        try {
            toServer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void send(JSONObject json) {
        try {
            System.out.println("about to send" + json);
            toServer.writeObject(json);
            System.out.println("Sent successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void request(Request request) {
        //TODO use all different requests...
        if(request == null) {
            System.out.println("unknown request...");
        } else {
            switch (request) {
                case APPOINTMENTSOFUSER:
                    break;
                case ALARMOFAPPOINTMENT:
                    break;
                case DATEOFAPPOINTMENT:
                    break;
                case TIMEOFAPPOINTMENT:
                    break;
                case DESCRIPTIONOFAPPOINTMENT:
                    break;
                case LISTOFAPPOINTMENTSFORNOTIFICATIONSOFUSER:
                    break;
                case FROMDATE:
                    break;
                case FROMTIME:
                    break;
                case PARTICIPANTSOFAPPOINTMENT:
                    break;
                case OWNEROFAPPOINTMENT:
                    break;
                case GETAPPOINTMENT:
                    break;
            }
        }
    }

}
