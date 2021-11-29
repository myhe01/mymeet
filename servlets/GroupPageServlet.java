import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(
    name = "group",
    urlPatterns = "/group/"
)

public class GroupPageServlet extends HttpServlet {
    private String message;
    private static final long serialVersionUID = 1L;
    private Group group = new Group();

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
        
        String groupID = request.getParameter("groupID");

        try {
            group = Query.groupByGroupID(Integer.parseInt(groupID));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.setContentType("text/plain");
        PrintWriter printWriter = response.getWriter();

        printWriter.println(group.getGroupName());
        
        ArrayList<String> interests = group.getInterestsList();
        for (String s: interests) {
            printWriter.println(s);
        }

        printWriter.println(group.getMessage());

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
