package database.repository;

import database.DatabaseConnection;
import database.UserService;
import helperclasses.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return user;
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
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
