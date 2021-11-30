import java.io.*;
import java.util.*;

import javax.management.Query;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(
    name = "settings",
    urlPatterns = "/settings/"
)

public class Settings extends HttpServlet {
    private String message;
    private static final long serialVersionUID = 1L;
    private User user;

    public void setMessage(String m) {
        this.message = m;
    }

    public String getMessage() {
        return this.message;
    }

    // Required method to accept the GET request
    // Gets data from the server
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String userID = request.getParameter("userID");

        try {
            user = Query.userByUserID(Integer.parseInt(userID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Required method to accept a POST request
    // Posts data to the server
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            
        try {
            Query.deleteUser(user.getUserID());

            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } catch (UserNotFound e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        // Perform something at end of servlet cycle
    }
}
