package server;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.StringWriter;
import java.util.prefs.Preferences;
import helperclasses.User;
import helperclasses.MeetingRoom;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectWriter;

/**
 * Created by espen on 11.03.14.
 */
public class jsonJacksonTest {
    private static final String jsonFilePath = "C:/git/fellesprosjekt/src/main/java/server/usertest.json";


    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        //writeJsonToFile(n1);
        MeetingRoom mr = new MeetingRoom();
        mr.setCapacity(25);
        mr.setRoom("myRoom");
        StringWriter stringRoom = new StringWriter();
        try {
            mapper.writeValue(stringRoom, mr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        meetRoom = readJsonFromString(stringRoom.toString(), MeetingRoom.class);
        User n1 = new User("Adolf");
        n1.setEmail("adolf@gmail.com");
        n1.setName("Adolf Boss");
        String userString = objectToJson(n1);
        System.out.println(userString);
        readJsonFromString2(userString);


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
    public static Object readJsonFromString(String json, Object classValue) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object n2 = mapper.readValue(json, classValue.class);
            return n2;

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null
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
    public static void readJsonFromString2(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            User n2 = mapper.readValue(json, User.class);
            System.out.println(n2.getUsername());

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
