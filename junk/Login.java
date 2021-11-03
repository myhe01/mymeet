/*
-   File name:      Login.java
-
-   Purpose:        To provide a user with access to log in to myMeet as well as creaming an
-                   account.
-
-   Methods:
-
*/

import java.io.*;
import javax.swing.*;

public class Login {
    JFrame f = new JFrame();
    JButton login;
    JButton register;
    JFormattedTextField username;
    JFormattedTextField password;

    public Login() {
        login = new JButton("Log in");
        register = new JButton("Register");
        username = new JFormattedTextField("Username");
        password = new JFormattedTextField("Password");

        login.setBounds(100, 400, 200, 50);
        register.setBounds(400, 400, 200, 50);
        username.setBounds(100, 100, 200, 50);
        password.setBounds(100, 175, 200, 50);

        f.add(login);
        f.add(register);
        f.add(username);
        f.add(password);

        f.setSize(1920, 1080); 

        f.setLayout(null);  
        f.setVisible(true);    
    }

    public static void main (String[] args) {
        Login login = new Login();

    }
}