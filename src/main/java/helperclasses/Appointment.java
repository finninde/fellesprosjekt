package helperclasses;

import UI.ViewScreen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kradalby on 05/03/14.
 */
public class Appointment implements Serializable{

    private String title;
    private String description;
    private TimeFrame timeFrame;
    private ArrayList<Participant> participants;
    private User owner;
    private MeetingRoom room;
    private String location;
    private int id;

    public Appointment(String title) {
        this.title = title;
    }

    public void addUser(User user) {
        if (this.participants == null) this.participants = new ArrayList<Participant>();
        Participant participant = new Participant();
        participant.setUser(user);
        participant.setStatus(Status.PENDING);

        this.participants.add(participant);
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

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
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

    public void addPropertyListener(ViewScreen viewScreen) {

    }
}
