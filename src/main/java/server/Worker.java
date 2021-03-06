package server;

import database.repository.AppointmentRepository;
import database.repository.UserRepository;
import helperclasses.*;
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
        System.out.println(obj);
        JSONObject json = new JSONObject();
        UserRepository ur = new UserRepository();
        AppointmentRepository ar = new AppointmentRepository();
        Request request = (Request) obj.get("request");
        if(request==null) {
            System.out.println("unkown request");
        } else {
            switch (request) {
                case APPOINTMENTSWHEREUSERISOWNER:
                    ArrayList<Appointment> appointments = ur.getAppointmentsWhereUserIsOwner(username);
                    json.put("response",request);
                    json.put("appointments", appointments);
                    json.put("key",obj.get("key"));
                    sendJSON(json);
                case APPOINTMENTSWHEREUSERISPARTICIPANT:
                    ArrayList<Appointment> appointmentsP = ur.getAppointmentsWhereUserIsParticipant(username);
                    json.put("response",request);
                    json.put("appointments", appointmentsP);
                    json.put("key",obj.get("key"));
                    sendJSON(json);
                case GETUSERS:
                    ArrayList<User> users = ur.getUsers();
                    json.put("response", request);
                    json.put("users",users);
                    json.put("key",obj.get("key"));
                    sendJSON(json);
                case ALARMOFAPPOINTMENT:
                    break;
                case UPDATEAPPOINTMENT:
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
                case GETSTATUSFORAPPOINTMENT:
                    ArrayList<Participant> participantsForAppointment = ar.getParticipants((int)obj.get("appointmentid"));
                    User thisUser = ur.getUser(username);
                    for(Participant participant: participantsForAppointment) {
                        if(participant.getUser().equals(thisUser)) {
                            json.put("status",participant.getStatus());
                        }
                    json.put("response",request);
                    json.put("key",obj.get("key"));
                    sendJSON(json);
                    }
                case UPDATEPARTICIPANTSTATUS:
                    int appointmentID = (int)json.get("appointmentID");
                    Status status = (Status)json.get("status");
                    ar.updateParticipantStatus(appointmentID, status, username);
                case PARTICIPANTSOFAPPOINTMENT:
                    ArrayList<Participant> participants = ar.getParticipants((int)obj.get("appointmentid"));
                    json.put("response", request);
                    json.put("participants",participants);
                    json.put("key",obj.get("key"));
                    sendJSON(json);
                case LOGOUT:
                    server.removeWorker(username);
                    json.put("response", Request.LOGOUT);
                    json.put("success", true);
                    sendJSON(json);
                    break;
                case GETLOGGEDINUSER:
                    User user = ur.getUser(username);
                    json.put("response",request);
                    json.put("user",user);
                    json.put("key",obj.get("key"));
                    sendJSON(json);
                case ADDAPPOINTMENT:
                    ar.addAppointment((Appointment) obj.get("appointment"));
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
