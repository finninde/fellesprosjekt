package server;

import org.json.simple.JSONObject;

/**
 * Created by espen on 13.03.14.
 */
public interface ConnectionListener {
    public void recievedMessage(JSONObject obj);
    public boolean running();
    public void disconnect();
}
