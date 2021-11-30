import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet(
        name = "searchevent",
        urlPatterns = "/searchevent"
)
public class SearchEventServlet extends HttpServlet {

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
        
        Integer eventID = request.getParameter("eventID");
        Event searchedEvent = null;
        try {
            searchedEvent = Query.eventByEventID(eventID);
            // redirect to page of event

            request.setAttribute("eventID","eventID");
            request.getRequestDispatcher("EventPage.jsp").forward(request, response);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void destroy() {

    }
}
