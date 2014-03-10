package database;

import database.repository.UserRepository;
import helperclasses.User;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kradalby
 * Date: 08/03/14
 * Time: 13:03
 * To change this template use File | Settings | File Templates.
 */
public class Populate {

    public static void main(String args[]) {

        //Testing stuff

        UserService us = new UserRepository();
        User kristoffer = new User("kradalby", "frozen", "Kristoffer Dalby", "kradalby@kradalby.no");
        User finn = new User("finn", "finn", "Finn Inderhaug", "finn@finn.no");
        User andreas = new User("andreas", "andreas", "Andreas Wien", "andreas@online.ntnu.no");
        User christoffer = new User("christoffer", "christoffer", "Christoffer Nysæther", "christoffer@christoffer.no");
        User jonas = new User("jonas", "jonas", "Jonas Dalseth", "jonas@jonas.no");
        User espen = new User("espen", "espen", "Espen Albert", "espen@espen.no");

        us.addUser(espen);
        us.addUser(kristoffer);
        us.addUser(finn);
        us.addUser(andreas);
        us.addUser(christoffer);
        us.addUser(jonas);


//        ArrayList<User> users = us.getUsers();
        User user = us.getUser("kradalby");
        System.out.println(user.getEmail());

    }

}
