/*
-   File name:      EmailTest.java
-
-   Compile:        javac -cp "./libs/javax.mail.jar" EmailTest.java
-
-   Purpose:        To test elements of the JavaMail
-
-   Methods:        None
-
-   Notes:          
-
*/

import java.io.*;
import java.util.*;
import javax.mail.*;

public class EmailTest {
    public static void main (String[] args) {
        final String username = "cpbro123@gmail.com";
        final String password = "";
        
        String to = "cpbro123@gmail.com";
        String from = "cpbro123@gmail.com";
        String subject = "JavaMail Test";

        Properties prop = new Properties();
        Session session;
        Message message;

		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        session = Session.getInstance(prop,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        

    }
}