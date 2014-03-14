package database.repository;

import com.mysql.jdbc.Statement;
import database.DatabaseConnection;
import database.MeetingRoomService;
import helperclasses.Appointment;
import helperclasses.MeetingRoom;
import helperclasses.TimeFrame;
import org.joda.time.DateTime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by kradalby on 06/03/14.
 */
public class MeetingRoomRepository implements MeetingRoomService {
    @Override
    public MeetingRoom getMeetingRoom(int id) {
        MeetingRoom mr = null;
        String sql = "SELECT * FROM MEETINGROOM WHERE ID = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                mr = new MeetingRoom();
                mr.setId(rs.getInt("ID"));
                mr.setCapacity(rs.getInt("CAPACITY"));
                mr.setRoom(rs.getString("ROOM"));
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return mr;
    }

    @Override
    public ArrayList<MeetingRoom> getAllMeetingRooms() {
        ArrayList<MeetingRoom> mrs = null;
        String sql = "SELECT * FROM MEETINGROOM";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            mrs = new ArrayList<MeetingRoom>();
            while (rs.next()) {
                MeetingRoom mr = new MeetingRoom();
                mr.setId(rs.getInt("ID"));
                mr.setCapacity(rs.getInt("CAPACITY"));
                mr.setRoom(rs.getString("ROOM"));
                mrs.add(mr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mrs;
    }

    @Override
    public void addMeetingRoom(MeetingRoom mr) {
        String sql = "INSERT INTO MEETINGROOM (ROOM, CAPACITY) VALUES (?,?)";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, mr.getRoom());
            statement.setInt(2, mr.getCapacity());

            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            key.next();
            mr.setId(key.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public ArrayList<TimeFrame> getTimeFramesForMeetingRoom(MeetingRoom mr) {
        ArrayList<TimeFrame> tfs = null;
        String sql = "select t.ID, t.STARTDATE, t.ENDDATE from MEETINGROOM m, APPOINTMENT a, TIMEFRAME t where m.ID = a.MEETINGROOMID and a.TIMEFRAMEID = t.ID and m.ID = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setInt(1, mr.getId());
            ResultSet rs = statement.executeQuery();
            tfs = new ArrayList<TimeFrame>();
            while (rs.next()) {
                TimeFrame tf = new TimeFrame(new DateTime(rs.getDate("STARTDATE")), new DateTime(rs.getDate("ENDDATE")));
                tfs.add(tf);
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return tfs;
    }

    @Override
    public void updateMeetingRoom(MeetingRoom mr) {
        String sql = "UPDATE MEETINGROOM SET ROOM=?, CAPACITY=? WHERE ID=?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setString(1, mr.getRoom());
            statement.setInt(2, mr.getCapacity());
            statement.setInt(3, mr.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
