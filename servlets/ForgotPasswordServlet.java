import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet(
        name = "passwordrecovery",
        urlPatterns = "/passwordrecovery"
)
public class ForgotPasswordServlet extends HttpServlet {

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

        try{
            Integer userID = Query.userByUsername(userName);
            User currentUser = Query.userByUserID(userID);
            currentUser.password = request.getParameter("password");
            
            Query.updateUser(currentUser);
            // TODO: Insert pyscript
        }
        catch (Exception e)
        {
            
        }


    }
    public void destroy() {

    }
}
