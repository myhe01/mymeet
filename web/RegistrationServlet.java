import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet(
        name = "registration",
        urlPatterns = "/registration"
)
public class RegistrationServlet extends HttpServlet, Query {

    private String message;
    private static final long serialVersionUID = 1L;

    // Required method to initialize Servlet
    public void init() throws ServletException{

    }

    // Required method to accept the GET request
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

//        response.setContentType("text/html");
//        PrintWriter printWriter = response.getWriter();
//        printWriter.print("<html>");
//        printWriter.print("<body>");
//        printWriter.print("<h1>Student Registration Form Data</h1>");
//        printWriter.print("<p> firstName :: " + firstName + "</p>");
//        printWriter.print("<p> lastName :: " + lastName + "</p>");
//        printWriter.print("<p> userName :: " + userName + "</p>");
//        printWriter.print("<p> userEmail :: " + userEmail + "</p>");
//        printWriter.print("<p> password :: " + password + "</p>");
//        printWriter.print("<body>");
//        printWriter.print("<html>");
//        printWriter.close();
//
//        System.out.println("firstname :: " + firstName);
//        System.out.println("email :: " + userEmail);

    }

    // Required method to accept a POST request
    //
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");

        User newUser = new User(firstName, lastName, userName, userEmail, password);
        databaseConnect dbQuery = new databaseConnect();

        try {
            Query.addUser(newUser);
        } catch (UserNotFound e){
            e.printStackTrace;
        }

    }
    public void destroy() {

    }
}
