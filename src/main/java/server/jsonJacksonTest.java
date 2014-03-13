package server;

import helperclasses.MeetingRoom;
import helperclasses.User;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by espen on 11.03.14.
 */
public class jsonJacksonTest {
    private static final String jsonFilePath = "C:/git/fellesprosjekt/src/main/java/server/usertest.json";


    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject obj = new JSONObject();
        JSONArray userList = new JSONArray();
        ArrayList<String> userList2 = new ArrayList<String>();
        ArrayList<User> userList3 = new ArrayList<User>();

        //writeJsonToFile(n1);
        MeetingRoom mr = new MeetingRoom();
        mr.setCapacity(25);
        mr.setRoom("myRoom");
        String stringRoom;
        stringRoom = objectToJson(mr);
        //readJsonFromString(stringRoom.toString(), MeetingRoom.class);
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


//        userList3.add(n1);
//        userList3.add(n2);
//        obj.put("User", userList3);
//        ArrayList<User> updatedList = (ArrayList<User>)obj.get("User");
//        User u1 = updatedList.get(0);
//        User u2 = updatedList.get(1);
//        System.out.println("user 1: " + u1.getName() + " user 2: "+ u2.getName());
//        System.out.println(obj);

//        String userString = objectToJson(n1);
//        String userString2 = objectToJson(n2);
//        userList2.add(userString);
//        userList2.add(userString2);
//        System.out.println(userString);
//        obj.put("User", userList2);
//        System.out.println(obj);
//        ArrayList<String> userListGet = (ArrayList<String>) obj.get("User");
//        System.out.println(userListGet);
//        User u1 = readJsonFromString2(userListGet.get(0));
//        User u2 = readJsonFromString2(userListGet.get(1));
//        System.out.println("user 1: " + u1.getName() + " user 2: "+ u2.getName());

//        readJsonFromString2((String)obj.get("User").get(1));
//        obj.put("Room", stringRoom);
//        System.out.println(obj);
//        MeetingRoom meetingRoom = readMeetingRoomFromString((String)obj.get("Room"));
//        System.out.println(meetingRoom.getRoom());



    }
    public static User readJsonFromString2(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            User n2 = mapper.readValue(json, User.class);
            return n2;

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String objectToJson(Object o) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(o);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static MeetingRoom readMeetingRoomFromString(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            MeetingRoom n2 = mapper.readValue(json, MeetingRoom.class);
            return n2;

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static void writeJsonToFile(Object n1) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            File jsonFile = new File(jsonFilePath);
            mapper.writeValue(jsonFile, n1);
            System.out.println(mapper.writeValueAsString(n1));
        } catch(JsonGenerationException ex) {
            ex.printStackTrace();
        } catch(JsonMappingException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void readJsonFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File jsonFile = new File(jsonFilePath);
            User user = mapper.readValue(jsonFile, User.class);
            System.out.println(user.getUsername());
        } catch(JsonGenerationException ex) {
            ex.printStackTrace();
        } catch(JsonMappingException ex) {

        } catch(IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Not yet implemented....");
    }
}
