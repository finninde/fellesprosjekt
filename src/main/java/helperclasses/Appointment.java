package helperclasses;

import UI.ViewScreen;

import java.io.Serializable;
import java.util.ArrayList;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;



/**
 * Created by kradalby on 05/03/14.
 */
public class Appointment implements Serializable {

    private String title;
    private String description;
    private TimeFrame timeFrame;
    private ArrayList<Participant> participants;
    private User owner;
    private MeetingRoom room;
    private String location;
    private int id;
    private PropertyChangeSupport pcs;

    public Appointment(String title) {
        this.title = title;
        pcs = new PropertyChangeSupport(this);
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
        pcs.firePropertyChange("title", this.title, title);
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        pcs.firePropertyChange("description", this.description, description);
        this.description = description;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void changeTimeFrame(TimeFrame timeFrame) {
        pcs.firePropertyChange("timeframe", this.timeFrame, timeFrame);
        this.timeFrame = timeFrame;
    }


    public void setTimeFrame(TimeFrame timeFrame) {
        pcs.firePropertyChange("timeframe", this.timeFrame, timeFrame);
        this.timeFrame = timeFrame;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        pcs.firePropertyChange("participants", this.participants, participants);
        this.participants = participants;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        pcs.firePropertyChange("owner", this.owner, owner);
        this.owner = owner;
    }

    public MeetingRoom getRoom() {
        return room;
    }

    public void setRoom(MeetingRoom room) {
        pcs.firePropertyChange("room", this.room, room);
        this.room = room;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        pcs.firePropertyChange("location", this.location, location);
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
