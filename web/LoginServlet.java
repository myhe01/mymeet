import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet(
        name = "login",
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet {

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

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

       
        try {
            Integer userID = Query.userByUsername(userName);
            User currentUser = Query.userByUserID(userID);

            if (currentUser.getPassword().equals(password))
            {
                // TODO: login user in 
            }
            
            else
            {
                // TODO: decline login attempt
                // message: incorrect login
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public void destroy() {

    }
}
