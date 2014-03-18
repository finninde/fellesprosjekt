package helperclasses;

import java.io.Serializable;

/**
 * Created by kradalby on 17/03/14.
 */
public class Participant implements Serializable {

    private User user;
    private Status status;

    public Participant() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
