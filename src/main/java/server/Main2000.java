package server;

import org.joda.time.DateTime;

/**
 * Created by espen on 12.03.14.
 */
public class Main2000 {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("heyhey");
        for(int i = 0;i<5;i++) {
            System.out.println(i);
        }
        Server server = new Server(6789);

        System.out.println("hey");
        int count = 0;
        while(true) {
            count += 1;
            System.out.println(count);
            if (count > 10){ break;}
            DateTime dateTime = new DateTime();
            dateTime.wait(500);

        }
    }
    public static void printString(String s) {
        System.out.println(s);
    }
}
