package database.repository;

import com.mysql.jdbc.Statement;
import database.AppointmentService;
import database.DatabaseConnection;
import database.MeetingRoomService;
import database.UserService;
import helperclasses.Appointment;
import helperclasses.TimeFrame;
import helperclasses.User;
import org.joda.time.DateTime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by kradalby on 06/03/14.
 */
public class AppointmentRepository implements AppointmentService {

    public AppointmentRepository() {
    }

    @Override
    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO APPOINTMENT (DESCRIPTION, LOCATION, OWNER, TIMEFRAMEID, MEETINGROOMID, TITLE) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, appointment.getDescription());
            statement.setString(2, appointment.getLocation());
            statement.setString(3, appointment.getOwner().getUsername());
            addTimeFrame(appointment.getTimeFrame());
            statement.setInt(4, appointment.getTimeFrame().getId());
            statement.setInt(5, appointment.getRoom().getId());
            statement.setString(6, appointment.getTitle());
            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            key.next();
            appointment.setId(key.getInt(1));
            addParticipants(appointment.getParticipants(), appointment);

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void addTimeFrame(TimeFrame timeFrame) {
        String sql = "INSERT INTO TIMEFRAME (STARTDATE, ENDDATE) VALUES (?,?)";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setDate(1, new java.sql.Date(timeFrame.getStartDate().getMillis()));
            statement.setDate(2, new java.sql.Date(timeFrame.getEndDate().getMillis()));
            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            key.next();
            timeFrame.setId(key.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Override
    public void addParticipant(User user, Appointment appointment) {
        String sql = "INSERT INTO PARTICIPANT (USERID, APPOINTMENTID, STATUS) VALUES (?,?,?)";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, user.getUsername());
            statement.setInt(2, appointment.getId());
            statement.setString(3, "Invited");
            System.out.println(statement.toString());
            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Override
    public void addParticipants(ArrayList<User> users, Appointment appointment) {
        for (User u : users) {
             addParticipant(u, appointment);
        }
    }

    @Override
    public Appointment getAppointment(int id) {
        UserService us = new UserRepository();
        MeetingRoomService mrs = new MeetingRoomRepository();
        Appointment appointment = null;
        String sql = "SELECT a.ID, a.TITLE, a.DESCRIPTION, a.LOCATION, a.OWNER, a.MEETINGROOMID, t.STARTDATE, t.ENDDATE FROM APPOINTMENT a, TIMEFRAME t WHERE a.ID = ? AND a.TIMEFRAMEID = t.ID";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                appointment = new Appointment(rs.getString("a.TITLE"));
                appointment.setId(rs.getInt("a.ID"));
                appointment.setDescription(rs.getString("a.DESCRIPTION"));
                appointment.setLocation(rs.getString("a.LOCATION"));
                appointment.setOwner(us.getUser(rs.getString("a.OWNER")));
                appointment.setTimeFrame(new TimeFrame(new DateTime(rs.getDate("t.STARTDATE")), new DateTime(rs.getDate("t.ENDDATE"))));
                appointment.setParticipants(getParticipants(appointment));
                appointment.setRoom(mrs.getMeetingRoom(rs.getInt("a.MEETINGROOMID")));

            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return appointment;
    }

    @Override
    public TimeFrame getTimeFrame(int id) {
        return null;
    }

    @Override
    public ArrayList<User> getParticipants(Appointment appointment) {
        ArrayList<User> users = null;
        UserService us = new UserRepository();
    String sql = "SELECT * FROM PARTICIPANT WHERE APPOINTMENTID = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setInt(1, appointment.getId());
            ResultSet rs = statement.executeQuery();
            users = new ArrayList<User>();
            while (rs.next()) {
                users.add(us.getUser(rs.getString("USERID")));

            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return users;
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        String sql = "UPDATE APPOINTMENT SET DESCRIPTION=?, LOCATION=?, OWNER=?, TIMEFRAMEID=?, MEETINGROOMID=?, TITLE=? WHERE ID=?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, appointment.getDescription());
            statement.setString(2, appointment.getLocation());
            statement.setString(3, appointment.getOwner().getUsername());
            statement.setInt(4, appointment.getTimeFrame().getId());
            statement.setInt(5, appointment.getRoom().getId());
            statement.setString(6, appointment.getTitle());
            statement.setInt(7, appointment.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void updateTimeFrame(TimeFrame timeFrame) {
        String sql = "UPDATE TIMEFRAME SET STARTDATE=?, ENDDATE=? WHERE ID=?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setDate(1, new java.sql.Date(timeFrame.getStartDate().getMillis()));
            statement.setDate(2, new java.sql.Date(timeFrame.getEndDate().getMillis()));
            statement.setInt(3, timeFrame.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Override
    public void deleteParticipant(User user, Appointment appointment) {
        String sql = "DELETE FROM PARTICIPANT WHERE USERID=? AND APPOINTMENTID=?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, user.getUsername());
            statement.setInt(2, appointment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        String sql = "DELETE FROM APPOINTMENT WHERE ID=?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, appointment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

}
