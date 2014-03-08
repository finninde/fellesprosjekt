package database;

import helperclasses.User;

import java.util.ArrayList;

/**
 * Created by kradalby on 06/03/14.
 */
public interface UserService {

    public ArrayList<User> getUsers();
    public User getUser(String username);

    public void addUser(User user);

}
