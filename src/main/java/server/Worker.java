package server;

import database.repository.AppointmentRepository;
import database.repository.UserRepository;
import helperclasses.Appointment;
import helperclasses.Request;
import helperclasses.User;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by espen on 13.03.14.
 */
public class Worker extends Thread implements ConnectionListener{

    private String username;
    private Socket connection;
    private Server server;
    private ObjectOutputStream toClient;
    private boolean running;

    public Worker(Server server, Socket connection, String username, Receiver receiver,ObjectOutputStream toClient) {
        running = true;
        System.out.println("worker created");
        receiver.changeListener(this);
        this.username = username;
        this.connection = connection;
        this.server = server;
        this.toClient = toClient;
        this.start();
    }

    public void run() {
        server.addWorker(username,this);
        welcomeMessage();
        while(running) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("worker dyying...");
    }

    public void sendJSON(JSONObject json) {
        try {
            System.out.println("Sending message"+ json);
            toClient.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void welcomeMessage() {
        JSONObject obj = new JSONObject();
        obj.put("response", Request.LOGIN);
        obj.put("success", true);
        sendJSON(obj);

    }

    @Override
    public void recievedMessage(JSONObject obj) {
        //TODO not yet implemented... This method should handle all possible requests from the user...
        System.out.println(obj);
        JSONObject json = new JSONObject();
        Request request = (Request) obj.get("request");
        if(request==null) {
            System.out.println("unkown request");
        } else {
            switch (request) {
                case APPOINTMENTSOFUSER:
                    break;
                case GETUSERS:
                    UserRepository ur = new UserRepository();
                    ArrayList<User> users = ur.getUsers();
                    json.put("response", request);
                    json.put("users",users);
                    sendJSON(json);
                case UPDATEAPPOINTMENT:
                    AppointmentRepository ar = new AppointmentRepository();

                case ALARMOFAPPOINTMENT:
                    break;
                case UPDATEAPPOINTMENT:
                    AppointmentRepository ar = new AppointmentRepository();
                    Appointment ap = (Appointment) obj.get("appointment");
                    String message= ar.updateAppointment(ap);
                    json.put("response", request);
                    boolean success = message.equals("ok") ? true: false;
                    json.put("success",success);
                    if(success) {
                        sendJSON(json);
                        break;
                    }
                    json.put("error", message);
                    sendJSON(json);
                    break;
                case PARTICIPANTSOFAPPOINTMENT:
                    break;
                case GETAPPOINTMENT:
                    break;
                case GETDATA:
                    System.out.println("get data request");
                    Appointment ap1 = new Appointment("Appointment1");
                    ArrayList<Appointment> appointments = new ArrayList<Appointment>();
                    appointments.add(ap1);
                    json.put("response",request);
                    json.put("appointments",appointments);
                    sendJSON(json);
                    break;
                case LOGOUT:
                    server.removeWorker(username);
                    json.put("response", Request.LOGOUT);
                    json.put("success", true);
                    sendJSON(json);
                    break;
            }
        }
    }

    @Override
    public boolean running() {
        return running;
    }

    @Override
    public void disconnect() {
        server.removeWorker(username);
        if(toClient != null) {
            try {
                running = false;
                toClient.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
