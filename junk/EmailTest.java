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

/*
import java.io.*;
import java.util.*;
import javax.mail.*;
*/

import java.io.*;
import java.util.Properties;
import java.util.Date;

import javax.mail.*;
import javax.mail.internet.*;


public class EmailTest {
    public static void main (String[] args) {
        final String username = "dev.mymeet@gmail.com";
        final String password = "buYbx*M@";
        
        String to = "collegebren@knights.ucf.edu";
        String from = "dev.mymeet@gmail.com";
        String subject = "JavaMail Test";
        String body = "This is a test of the JavaMail library.\n\nSincerely,\nBrendan";

        Properties prop = new Properties();
        Session session;
        MimeMessage message;

		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");

        session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        session.setDebug(true);

        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            message.AddRecipient(Message.RecipientType.TO, InternetAddress.parse(to, false));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Message sent.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}