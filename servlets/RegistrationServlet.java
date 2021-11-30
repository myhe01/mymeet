import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet(
        name = "registration",
        urlPatterns = "/registration"
)
public class RegistrationServlet extends HttpServlet {

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

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter().println("<h1>im in doPost!</h1>");
        User newUser = new User(firstName, lastName, userName, userEmail, password);

        try {
            newUser = Query.addUser(newUser);
            Integer newUserID = newUser.getId();

            request.setAttribute("userID", "newUserID");
            request.getRequestDispatcher("UserPage.jsp").forward(request, response);
        } catch (UserNotFound e){
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter().println("<h1>User not found!</h1>");
        }

    }
    public void destroy() {

    }
}
