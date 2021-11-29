import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(
    name = "user",
    urlPatterns = "/user/"
)

public class UserPageServlet extends HttpServlet {
    private String message;
    private static final long serialVersionUID = 1L;
    private User user;

    public void setMessage(String m) {
        this.message = m;
    }

    public String getMessage() {
        return this.message;
    }

    // Required method to initialize Servlet
    public void init() throws ServletException{
        try {
            Query.isOnline();
        } catch (SQLException e) {
            throw new ServletException();
            setMessage("Unable to access database.");
            return;
        } 
            
        setMessage("Welcome to myMeet.");
    }

    // Required method to accept the GET request
    // Gets data from the server
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String userID = request.getParameter("userID");

        try {
            user = Query.userByUserID(Integer.parseInt(userID));
        } catch (Exception e) {
            // TODO: exception handling
        }
        
        response.setContentType("text/plain");
        PrintWriter printWriter = response.getWriter();

        printWriter.println(user.getFirstName());
        printWriter.println(user.getLastName());
        printWriter.println(user.getUserName());
        
        ArrayList<String> interests = user.getInterests();
        for (String s: interests) {
            printWriter.println(s);
        }

        printWriter.close();
    }

    // Required method to accept a POST request
    // Posts data to the server
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    }

    public void destroy() {
        // Perform something at end of servlet cycle
    }
}
