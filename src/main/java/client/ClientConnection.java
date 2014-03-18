package client;

import UI.MainTest;
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
    public OwnerOfClientConnection owner;

    public ClientConnection(String address, int port, OwnerOfClientConnection owner){
        clientSocket = null;
        this.owner = owner;
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
        MainTest mainTest = new MainTest();
        ClientConnection client = new ClientConnection("78.91.51.78", 6789, mainTest);
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
        ArrayList<User> users = client.getUsers();
        System.out.println("users received successfully");

    }



    @Override
    public void recievedMessage(JSONObject obj) {
        System.out.println(obj);
        int key;
        Request response = (Request) obj.get("response");
        if(response == null) {
            return;
        } else {
            switch (response) {
                case APPOINTMENTSWHEREUSERISOWNER:
                    key = (int) obj.get("key");
                    incomingObjects.put(key, (ArrayList<Appointment>) obj.get("appointments"));
                case APPOINTMENTSWHEREUSERISPARTICIPANT:
                    key = (int) obj.get("key");
                    incomingObjects.put(key, (ArrayList<Appointment>) obj.get("appointments"));
                case ALARMOFAPPOINTMENT:
                    break;
                case GETUSERS:
                    key = (int) obj.get("key");
                    incomingObjects.put(key,(ArrayList<User>) obj.get("users"));
                    break;
                case PARTICIPANTSOFAPPOINTMENT:
                    key = (int) obj.get("key");
                    incomingObjects.put(key, (ArrayList<Participant>)obj.get("participants"));
                    break;
                case GETAPPOINTMENT:
                    break;
                case GETDATA:
                    System.out.println("getData json arrived");
                    key = (int) obj.get("key");
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

////////////////////// From here on out all the different GUI request comes ////////////////////////

    public ArrayList<User> getUsers() {
        JSONObject json = new JSONObject();
        json.put("request",Request.GETUSERS);
        key += 1;
        json.put("key", key);
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
        JSONObject json = new JSONObject();
        json.put("request", Request.PARTICIPANTSOFAPPOINTMENT);
        json.put("appointmentid", id);
        key += 1;
        json.put("key", key);
        send(json);
        ArrayList<Participant> participants = (ArrayList<Participant>) waitForObject(key);
        return participants;
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
    public ArrayList<Appointment> getAppointmentsWhereUserIsOwner() {
        JSONObject json = new JSONObject();
        json.put("request", Request.APPOINTMENTSWHEREUSERISOWNER);
        key += 1;
        json.put("key",key);
        send(json);
        ArrayList<Appointment> appointments = (ArrayList<Appointment>) waitForObject(key);
        return appointments;
    }

    @Override
    public ArrayList<Appointment> getAppointmentsWhereUserIsParticipant() {
        JSONObject json = new JSONObject();
        json.put("request", Request.APPOINTMENTSWHEREUSERISPARTICIPANT);
        key += 1;
        json.put("key",key);
        send(json);
        ArrayList<Appointment> appointments = (ArrayList<Appointment>) waitForObject(key);
        return appointments;
    }
}
