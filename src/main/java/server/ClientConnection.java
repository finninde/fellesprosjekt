package server;

import org.json.simple.JSONObject;

/**
 * Created by espen on 12.03.14.
 */
public class ClientConnection implements ConnectionListener{
    public ClientConnection(){
        System.out.println("hei");
    }

    @Override
    public void recievedMessage(JSONObject obj) {

    }
}
