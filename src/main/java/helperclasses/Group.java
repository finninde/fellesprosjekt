package helperclasses;

import java.util.ArrayList;

/**
 * Created by kradalby on 05/03/14.
 */
public class Group {

    private String groupName;
    private ArrayList<User> users;

    public boolean addUser() {
        return false;
    }

    public boolean removeUser() {
        return false;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
