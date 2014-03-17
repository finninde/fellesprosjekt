package server;

import helperclasses.MeetingRoom;
import helperclasses.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Created by espen on 11.03.14.
 */




public class jsonJacksonTest {
    private static final String jsonFilePath = "C:/git/fellesprosjekt/src/main/java/server/usertest.json";


    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        JSONArray userList = new JSONArray();
        ArrayList<String> userList2 = new ArrayList<String>();
        ArrayList<User> userList3 = new ArrayList<User>();

        MeetingRoom mr = new MeetingRoom();
        mr.setCapacity(25);
        mr.setRoom("myRoom");




        String stringRoom;
        User n1 = new User("Adolf");
        n1.setEmail("adolf@gmail.com");
        n1.setName("Adolf Boss");
        User n2 = new User("Per");
        n2.setEmail("per@gmail.com");
        n2.setName("Per Johnsen");

        obj.put("User",n1);
        obj.put("User2", n2);
        System.out.println(obj);
        User u1 = (User)obj.get("User");
        User u2 = (User) obj.get("User2");
        System.out.println("user 1: " + u1.getName() + " user 2: "+ u2.getName());


        userList3.add(n1);
        userList3.add(n2);
        obj.put("User", userList3);
        ArrayList<User> updatedList = (ArrayList<User>)obj.get("User");
        User u11 = updatedList.get(0);
        User u22 = updatedList.get(1);
        System.out.println("user 1: " + u11.getName() + " user 2: "+ u22.getName());
        System.out.println(obj);


    }

}