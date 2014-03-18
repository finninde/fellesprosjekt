package database;

import helperclasses.MeetingRoom;
import helperclasses.TimeFrame;

import java.util.ArrayList;

/**
 * Created by kradalby on 06/03/14.
 */
public interface MeetingRoomService {


    public MeetingRoom getMeetingRoom(int id);
    public ArrayList<MeetingRoom> getAllMeetingRooms();


    public void addMeetingRoom(MeetingRoom mr);
    public ArrayList<TimeFrame> getTimeFramesForMeetingRoom(MeetingRoom mr);

    public void updateMeetingRoom(MeetingRoom mr);
}
