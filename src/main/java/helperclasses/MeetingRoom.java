package helperclasses;

import database.MeetingRoomService;
import database.repository.MeetingRoomRepository;

import java.util.ArrayList;

/**
 * Created by kradalby on 05/03/14.
 */
public class MeetingRoom {

    private Integer id;
    private String room;
    private Integer capacity;
    // I dont think we need this, it is probably more reasonable to calculate this every time.
    //private ArrayList<TimeFrame> reservations;

    public MeetingRoom() {
    }


    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<TimeFrame> getReservations() {
        ArrayList<TimeFrame> reservations;
        MeetingRoomService mrs = new MeetingRoomRepository();
        reservations = mrs.getTimeFramesForMeetingRoom(this);
        return reservations;
    }

}
