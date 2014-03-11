package database.repository;

import com.mysql.jdbc.Statement;
import database.DatabaseConnection;
import database.MeetingRoomService;
import helperclasses.MeetingRoom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
