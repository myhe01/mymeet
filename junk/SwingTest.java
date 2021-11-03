/*
-   File name:      SwingTest.java
-
-   Purpose:        To test elements of the Swing library
-
-   Methods:        None
-
-   Notes:          X11 display must be present for program to work properly. Running through VSC
-                   is fine, but running through WSL will result in an error (essentially you need
-                   a GUI).
*/

import java.io.*;
import javax.swing.*;

public class SwingTest {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        JButton b = new JButton("Click me!");
        JButton c = new JButton("Don't click me.");

        // button layout: x-axis, y-axis, width, height  
        b.setBounds(100, 50, 200, 50);
        c.setBounds(100, 100, 200, 50);                  

        // add button to frame
        f.add(b);
        f.add(c);

        // frame layout: width, height  
        f.setSize(400, 300);   
        
        // using no layout managers  
        f.setLayout(null);   
        
        // making the frame visible  
        f.setVisible(true);                     
    }
}