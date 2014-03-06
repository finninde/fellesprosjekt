package helperclasses;

import java.util.ArrayList;

/**
 * Created by kradalby on 05/03/14.
 */
public class MeetingRoom {

    private String room;
    private Integer capacity;
    private ArrayList<TimeFrame> reservations;

    public boolean addReservation() {
        throw new NotYetImplementedException();
    }

    public boolean removeReservation() {
        throw new NotYetImplementedException();
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
}
