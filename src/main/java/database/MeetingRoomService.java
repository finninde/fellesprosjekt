package database;

import helperclasses.MeetingRoom;

/**
 * Created by kradalby on 06/03/14.
 */
public interface MeetingRoomService {


    public MeetingRoom getMeetingRoom(int id);

    public void addMeetingRoom(MeetingRoom mr);
}
