package helperclasses;

/**
 * Created by Wien on 10.03.14.
 */
        import java.io.UnsupportedEncodingException;
        import java.util.Properties;

        import javax.mail.Message;
        import javax.mail.MessagingException;
        import javax.mail.PasswordAuthentication;
        import javax.mail.Session;
        import javax.mail.Transport;
        import javax.mail.internet.AddressException;
        import javax.mail.internet.InternetAddress;
        import javax.mail.internet.MimeMessage;

public class SendMail {

    public SendMail(/*FILL IN DESIRED PARAMETERS*/String inviter, Appointment appointment, String invitedEmail) {

        /**
         * Set up the mail sender.
         */

        final String username = ""/*FILL IN DESIRED SENDER MAIL*/;			//mailadressen som sender mail
        final String password = ""/*FILL IN PASSWORD TO SENDER MAIL*/;						//passord

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

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(invitedEmail));					//mail-addresse til mottaker
            message.setSubject(inviter+" has invited you to his appointment");
            message.setText(inviter	+ " wants you to participate in the appointment..."/*FILL IN REST OF MESSAGE*/);

            Transport.send(message);

            System.out.println("Done");

        } catch (Exception e) {
            //insert error message
        }
    }
}
