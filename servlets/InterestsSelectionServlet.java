import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.ArrayList;



@WebServlet(
        name = "interestsselection",
        urlPatterns = "/interestsselection"
)
public class InterestsSelectionServlet extends HttpServlet {

    private String message;
    private static final long serialVersionUID = 1L;

    // Required method to initialize Servlet
    public void init() throws ServletException{

    }

    // Required method to accept the GET request
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String userName = (String) session.getAttribute("userID");


    }

    // Required method to accept a POST request
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("btn_submit") != null)
        {
            String interests[] = request.getParameterValues("chk_language");

            for(int = 0; i < language.length; i++)
            {
                ArrayList<String> interestList = interests[i];
            }

            User currentUser = Query.userByUserID("userID");
            currentUser.setInterests(interestList);
        }

    }
    public void destroy() {

    }
}
