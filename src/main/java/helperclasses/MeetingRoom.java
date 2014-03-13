package helperclasses;

import java.util.ArrayList;

/**
 * Created by kradalby on 05/03/14.
 */
public class MeetingRoom {

    private Integer id;
    private String room;
    private Integer capacity;
    private ArrayList<TimeFrame> reservations;

    public MeetingRoom() {
    }

    public boolean addReservation() {
        return false;
    }

    public boolean removeReservation() {
        return false;
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
}
