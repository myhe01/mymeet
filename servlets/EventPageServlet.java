import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(
    name = "event",
    urlPatterns = "/event/"
)

public class EventPageServlet extends HttpServlet {
    private String message;
    private static final long serialVersionUID = 1L;
    private Event event;

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
        
        String eventID = request.getParameter("eventID");
        Group hostGroup;

        try {
            event = Query.eventByEventID(Integer.parseInt(eventID));
            hostGroup = Query.groupbyGroupID(event.getGroupID());
        } catch (Exception e) {
            // TODO: exception handling
        }
        
        response.setContentType("text/plain");
        PrintWriter printWriter = response.getWriter();

        // Event name
        printWriter.println(event.getEventName());

        // Group name (host group)
        printWriter.println(hostGroup.getGroupName());

        // Username (creator)
        printWriter.println((Query.userByUserID(event.getCreator())).getUserName());

        // Location
        printWriter.println(event.getLocation());

        // Date
        printWriter.println(event.getDate());

        // Time
        printWriter.println(event.getTime());

        // Date end
        printWriter.println(event.getDateEnd());

        // Time end
        printWriter.println(event.getTimeEnd());

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
