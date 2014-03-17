package client;

import helperclasses.*;
import org.json.simple.JSONObject;
import server.ConnectionListener;
import server.Receiver;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by espen on 12.03.14.
 */
public class ClientConnection extends Thread implements ConnectionListener, GUIRequests{
    private boolean running;
    private HashMap<Integer, Object> incomingObjects;
    private int count;
    private int key;
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
        while(running) {
            try {
                count += 1;
                sleep(100);
                if(count == 100) { logout(); }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void logout() {
        System.out.println("about to logout");
        JSONObject json = new JSONObject();
        json.put("request", Request.LOGOUT);
        send(json);
    }
    public static void main(String args[]) {
        System.out.println("wallabaya!");
        ClientConnection client = new ClientConnection("78.91.51.78", 6789);
        JSONObject json = new JSONObject();
        json.put("request",Request.LOGIN);
        json.put("username", "espen");
        json.put("password", "1234");
        client.send(json);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Appointment> appointments = (ArrayList<Appointment>) client.request (Request.GETDATA);
        System.out.println("Received appointments successfully, appointment received: " + appointments.get(0).getTitle());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<User> users = client.getUsers();
        System.out.println("users received successfully");

    }

    public ArrayList<User> getUsers() {
        JSONObject json = new JSONObject();
        json.put("request",Request.GETUSERS);
        key += 1;
        send(json);
        ArrayList<User> users = (ArrayList<User>) waitForObject(key);
        return users;
    }

    @Override
    public ArrayList<Group> getGroups() {
        return null;
    }

    @Override
    public ArrayList<Participant> getParticipantsOfAppointment(int id) {
        return null;
    }

    @Override
    public Appointment getAppointment() {
        return null;
    }

    @Override
    public Alarm getAlarm() {
        return null;
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        JSONObject json = new JSONObject();
        json.put("request", Request.UPDATEAPPOINTMENT);
        json.put("appointment", appointment);
        send(json);

    }

    @Override
    public ArrayList<Appointment> getUsersAppointments() {
        return null;
    }


    @Override
    public void recievedMessage(JSONObject obj) {
        System.out.println(obj);
        Request response = (Request) obj.get("response");
        if(response == null) {
            return;
        } else {
            switch (response) {
                case APPOINTMENTSOFUSER:
                    break;
                case ALARMOFAPPOINTMENT:
                    break;
                case GETUSERS:
                    incomingObjects.put(key,(ArrayList<User>) obj.get("users"));
                    break;
                case PARTICIPANTSOFAPPOINTMENT:
                    break;
                case GETAPPOINTMENT:
                    break;
                case GETDATA:
                    System.out.println("getData json arrived");
                    incomingObjects.put(key, (ArrayList<Appointment>) obj.get("appointments"));
                    break;
                case LOGOUT:
                    if((boolean)obj.get("success")) {
                        disconnect();
                    }
                    break;
                case LOGIN:
                    if((boolean)obj.get("success")) {
                        System.out.println("login successful!");
                    }
                    break;
            }
        }
    }
    public Object waitForObject(Integer key) {
        while(true) {
            System.out.println(incomingObjects);
            if(incomingObjects.containsKey(key)){
                return incomingObjects.get(key);
            }
            try {
                Thread.sleep(1000);
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
        System.out.println("Disconnecting");
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

    public Object request(Request request) {
        //TODO use all different requests...
        if(request == null) {
            System.out.println("unknown request...");
        } else {
            JSONObject json = new JSONObject();
            switch (request) {
                case APPOINTMENTSOFUSER:
                    break;
                case ALARMOFAPPOINTMENT:
                    break;
                case PARTICIPANTSOFAPPOINTMENT:
                    break;
                case GETAPPOINTMENT:
                    break;
                case GETDATA:
                    json.put("request", request);
                    send(json);
                    key += 1;
                    return waitForObject(key);
            }
        }
        return null;
    }



}
