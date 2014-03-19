package helperclasses;

/**
 * Created by Wien on 10.03.14.
 */

        import java.util.Date;
        import java.util.Properties;

        import javax.mail.Message;
        import javax.mail.PasswordAuthentication;
        import javax.mail.Session;
        import javax.mail.Transport;
        import javax.mail.internet.InternetAddress;
        import javax.mail.internet.MimeMessage;

public class SendMail{

    public SendMail(Notifications type, User user, Appointment appointment, String invitedEmail, boolean notification) {

        /**
         * Set up the mail sender.
         */

        CalendarProperties properties = CalendarProperties.getInstance();

        final String username = properties.getEmailuser();  //mailadressen som sender mail
        final String password = properties.getEmailpass();  //passord

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        /**
         * Attempt to log in
         */

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
             }
         });


        String messageText = "";
        String subject = "";

        Date startDate = appointment.getTimeFrame().getStartDate().toDate();
        Date endDate = appointment.getTimeFrame().getEndDate().toDate();
        System.out.println(startDate);
        switch(type){
            case INVITEDTOAPPOINTMENT:{
                subject = "You have been invited to an appointment";
                messageText = user.getName()+" has invited you to the appointment, "+appointment.getTitle()+", at "+
                startDate+" to "+endDate+".";
                break;
            }
            case REPLIEDTOAPPOINTMENT:{
                subject = user.getName()+" has replied to your appointment";
                messageText = user.getName()+" has replied to your appointment, "+appointment.getTitle()+
                        ", at "+startDate+".";
                break;
            }
            case APPOINTMENTCANCELLED:{
                subject = "One of your appointments have been cancelled";
                messageText = "The appointment "+appointment.getTitle()+" at "+startDate+" has been cancelled.";
                break;
            }
            case APPOINTMENTCONFIRMED:{
                subject = "Appointment confirmed";
                messageText = "The appointment "+appointment.getTitle()+" has been confirmed and will take place "+
                        startDate+" to "+endDate+" at "+appointment.getLocation()+".";
                break;
            }
            case APPOINTMENTUPDATED:{
                subject = "Appointment has been updated";
                messageText = "The appointment "+appointment.getTitle()+" at "+startDate+" has been updated.";
                break;
            }
            default:{
                subject = "Default subject";
                messageText = "Default message";
                break;
            }
        }

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(invitedEmail)); //mail-addresse til mottaker
            message.setSubject(subject);
            message.setText(messageText);
            if (!notification){
                Transport.send(message);
                System.out.println("Done");
            }
            else{
                Transport.send(message);
                System.out.println("Done");
            }

        } catch (Exception e) {
            System.out.println("Some error occurred when attempting to send mail.");
        }
    }
}
