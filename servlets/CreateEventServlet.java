import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet(
        name = "createevent",
        urlPatterns = "/createevent"
)
public class CreateEventServlet extends HttpServlet {

    private String message;
    private static final long serialVersionUID = 1L;

    // Required method to initialize Servlet
    public void init() throws ServletException{

    }

    // Required method to accept the GET request
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    }

    // Required method to accept a POST request
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Event newEvent = new Event();
        
        newEvent.setEventName(request.getParameter("eventName"));
        newEvent.setGroupID(Integer.parseInt(request.getParameter("groupID")));
        newEvent.setCreator(Integer.parseInt(request.getParameter("creator")));
        newEvent.setLocation(request.getParameter("location"));
        newEvent.setDate(request.getParameter("date"));
        newEvent.setTime(request.getParameter("time"));
        newEvent.setDateEnd(request.getParameter("dateEnd"));
        newEvent.setTimeEnd(request.getParameter("timeEnd"));

        try {
            newEvent = Query.createEvent(newEvent);

            Integer newEventID = newEvent.getEventId();
            // hopefully routes
            request.setAttribute("eventID","newEventID");
            request.getRequestDispatcher("EventPage.jsp").forward(request, response);
        } catch (UserNotFound e){
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter().println("<h1>Event not found!</h1>");
        }
    }
    
    public void destroy() {

    }
}
