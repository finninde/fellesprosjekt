package database.repository;

import database.AppointmentService;
import database.DatabaseConnection;
import database.UserService;
import helperclasses.Appointment;
import helperclasses.TimeFrame;
import org.joda.time.DateTime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kradalby on 06/03/14.
 */
public class AppointmentRepository implements AppointmentService {

    public AppointmentRepository() {
    }

    @Override
    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO USER (USERNAME, PASSWORD, NAME, EMAIL) VALUES (?,?,?,?)";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void addTimeFrame(TimeFrame timeFrame) {
        String sql = "INSERT INTO USER (USERNAME, PASSWORD, NAME, EMAIL) VALUES (?,?,?,?)";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Override
    public Appointment getAppointment(int id) {
        UserService us = new UserRepository();
        Appointment appointment = null;
        String sql = "SELECT a.TITLE, a.DESCRIPTION, a.LOCATION, a.OWNER, t.STARTDATE, t.ENDDATE FROM APPLICATION a, TIMEFRAME t WHERE a.ID = ? AND a.TIMEFRAMEID = t.ID";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                appointment = new Appointment(rs.getString("a.TITLE"));
                appointment.setDescription(rs.getString("a.DESCRIPTION"));
                appointment.setLocation(rs.getString("a.LOCATION"));
                appointment.setOwner(us.getUser(rs.getString("a.OWNER")));
                appointment.setTimeFrame(new TimeFrame(new DateTime("t.STARTDATE"), new DateTime("t.ENDDATE")));
                //appointment.setParticipants();
                //appointment.setRoom();

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
}
