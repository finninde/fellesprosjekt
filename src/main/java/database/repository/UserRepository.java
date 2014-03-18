package database.repository;

import com.mysql.jdbc.Statement;
import database.AppointmentService;
import database.DatabaseConnection;
import database.UserService;
import helperclasses.Alarm;
import helperclasses.Appointment;
import helperclasses.User;
import org.joda.time.DateTime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kradalby on 06/03/14.
 */
public class UserRepository implements UserService {

    public UserRepository() {
    }

    @Override
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM USER";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setEmail(rs.getString("EMAIL"));
                users.add(user);
            }
        } catch (SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        }

        return users;
    }

    @Override
    public User getUser(String username) {
        User user = null;
        String sql = "SELECT * FROM USER WHERE USERNAME = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setEmail(rs.getString("EMAIL"));
            }
        } catch (SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        }

        return user;
    }

    @Override
    public ArrayList<Appointment> getAppointmentsWhereUserIsOwner(User user) {
        ArrayList<Appointment> apps = null;
        AppointmentService as = new AppointmentRepository();
        String sql = "select a.ID from USER u, APPOINTMENT a where u.USERNAME = a.OWNER and u.USERNAME = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setString(1, user.getUsername());
            ResultSet rs = statement.executeQuery();
            apps = new ArrayList<Appointment>();
            while (rs.next()) {
                Appointment app = as.getAppointment(rs.getInt("a.ID"));
                apps.add(app);

            }
        } catch (SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        }

        return apps;

    }

    @Override
    public ArrayList<Appointment> getAppointmentsWhereUserIsParticipant(User user) {
        ArrayList<Appointment> apps = null;
        AppointmentService as = new AppointmentRepository();
        String sql = "select a.ID from USER u, APPOINTMENT a, PARTICIPANT p where u.USERNAME = p.USERID and a.ID = p.APPOINTMENTID and u.USERNAME = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setString(1, user.getUsername());
            ResultSet rs = statement.executeQuery();
            apps = new ArrayList<Appointment>();
            while (rs.next()) {
                Appointment app = as.getAppointment(rs.getInt("a.ID"));
                apps.add(app);

            }
        } catch (SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        }

        return apps;
    }

//    @Override
//    public Alarm getAlarm(int id) {
//        Alarm alarm = null;
//        String sql = "SELECT * FROM  ALARM WHERE ID = ?";
//        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
//            statement.setInt(1, id);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                alarm = new Alarm();
//                alarm.setUser();
//                alarm.setAppointment();
//                alarm.setExecuteAlarm();
//            }
//        } catch (SQLException e) {
//    Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
//        }
//
//        return alarm;
//    }

    @Override
    public ArrayList<Alarm> getAllAlarmsForUser(User user) {
        ArrayList<Alarm> alarms = null;
        AppointmentService as = new AppointmentRepository();
        String sql = "select * from ALARM where USERID = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setString(1, user.getUsername());
            ResultSet rs = statement.executeQuery();
            alarms = new ArrayList<Alarm>();
            while (rs.next()) {
                Alarm alarm = new Alarm();
                alarm.setExecuteAlarm(new DateTime(rs.getDate("EXECUTEDATE")));
                alarm.setAppointment(as.getAppointment(rs.getInt("APPOINTMENTID")));
                alarm.setUser(user);
                alarm.setId(rs.getInt("ID"));

            }
        } catch (SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        }

        return alarms;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO USER (USERNAME, PASSWORD, NAME, EMAIL) VALUES (?,?,?,?)";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void addAlarm(Alarm alarm) {
        String sql = "INSERT INTO ALARM (USERID, APPOINTMENTID, EXECUTEDATE) VALUES (?,?,?)";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, alarm.getUser().getUsername());
            statement.setInt(2, alarm.getAppointment().getId());
            statement.setDate(3, new java.sql.Date(alarm.getExecuteAlarm().getMillis()));
            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            key.next();
            alarm.setId(key.getInt(1));

        } catch (SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public void updateAlarm(Alarm alarm) {
        String sql = "UPDATE ALARM SET USERID=?, APPOINTMENTID=?, EXECUTEDATE=? WHERE ID=?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, alarm.getUser().getUsername());
            statement.setInt(2, alarm.getAppointment().getId());
            statement.setDate(3, new java.sql.Date(alarm.getExecuteAlarm().getMillis()));
            statement.setInt(4, alarm.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE USER SET PASSWORD=?, NAME=?, EMAIL=? WHERE USERNAME=?";
        try (PreparedStatement statement = DatabaseConnection.getConnectionInstance().prepareStatement(sql);) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
