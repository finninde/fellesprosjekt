package helperclasses;

import java.util.ArrayList;

/**
 * Created by kradalby on 05/03/14.
 */
public class Appointment {

    private String title;
    private String description;
    private TimeFrame timeFrame;
    private ArrayList<User> participants;
    private User owner;
    private MeetingRoom room;
    private String location;
    private int id;

    public Appointment(String title) {
        this.title = title;
    }

    public boolean addUser() {
        return false;
    }

    public boolean removeUser() {
        return false;
    }

    public boolean addGroup() {
        return false;
    }

    public boolean removeGroup() {
        return false;
    }

    public void alertChanges() {
        System.out.println("dummy");
    }

    public boolean deleteAppointment() {
        return false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void changeTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }


    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public MeetingRoom getRoom() {
        return room;
    }

    public void setRoom(MeetingRoom room) {
        this.room = room;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
