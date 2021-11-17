import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(
        name = "homepage",
        urlPatterns = "/homepage/"
)
public class homeServlet extends HttpServlet{

    private String message;

    // Required method to initialize Servlet
    public void init() throws ServletException{
        message = "Welcome to MyMeet";
    }

    // Required method to accept the GET request
    //
    public void doGet(HttpServletResponse request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    // Required method to accept a POST request
    //
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    public void destroy() {

    }
}
