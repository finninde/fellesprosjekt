package database;

import database.repository.AppointmentRepository;
import database.repository.MeetingRoomRepository;
import database.repository.UserRepository;
import helperclasses.Appointment;
import helperclasses.MeetingRoom;
import helperclasses.TimeFrame;
import helperclasses.User;
import javafx.application.Application;
import org.joda.time.DateTime;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kradalby
 * Date: 08/03/14
 * Time: 13:03
 * To change this template use File | Settings | File Templates.
 */
public class Populate {

    public static void main(String args[]) {

        //Testing stuff

        UserService us = new UserRepository();
//        User kristoffer = new User("kradalby", "frozen", "Kristoffer Dalby", "kradalby@kradalby.no");
//        User finn = new User("finn", "finn", "Finn Inderhaug", "finn@finn.no");
//        User andreas = new User("andreas", "andreas", "Andreas Wien", "andreas@online.ntnu.no");
//        User christoffer = new User("christoffer", "christoffer", "Christoffer Nysæther", "christoffer@christoffer.no");
//        User jonas = new User("jonas", "jonas", "Jonas Dalseth", "jonas@jonas.no");
//        User espen = new User("espen", "espen", "Espen Albert", "espen@espen.no");
//
//        us.addUser(espen);
//        us.addUser(kristoffer);
//        us.addUser(finn);
//        us.addUser(andreas);
//        us.addUser(christoffer);
//        us.addUser(jonas);


//        ArrayList<User> users = us.getUsers();
        User user = us.getUser("kradalby");
        User user2 = us.getUser("espen");
//        System.out.println(user.getEmail());
//
        AppointmentService as = new AppointmentRepository();
        MeetingRoomService mrs = new MeetingRoomRepository();

//        Appointment a = as.getAppointment(9999);
//        MeetingRoom mr = mrs.getMeetingRoom(9);
//        System.out.println(mr.getRoom());
//        System.out.println(mr.getId());
//        mr.setRoom("p336");
//        mr.setCapacity(5);
//
//        mrs.updateMeetingRoom(mr);
//        System.out.println(mr.getRoom());
//        System.out.println(mr.getId());
//
//
//
//
//        TimeFrame tf = new TimeFrame(new DateTime(), new DateTime());
//        Appointment app = new Appointment("Test");
//        app.setTimeFrame(tf);
//        app.setOwner(user);
//        app.setRoom(mr);
//        app.setDescription("DERPDERMAEGDFG");
//        app.addUser(user2);
//
//        as.addAppointment(app);
//
//        System.out.println(app.getId());

//        ArrayList<Appointment> owner = us.getAppointmentsWhereUserIsOwner(user);
//        System.out.println(owner);
//        ArrayList<Appointment> participant = us.getAppointmentsWhereUserIsParticipant(user2);
//        System.out.println(participant);
//
//        System.out.println(owner.get(0).getTitle());
//        System.out.println(participant.get(0).getId());
    }
}
