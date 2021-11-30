import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet(
        name = "creategroup",
        urlPatterns = "/creategroup"
)
public class CreateGroupServlet extends HttpServlet {

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

        String groupName = request.getParameter("groupName");
        Group newGroup = new Group();
        newGroup.setGroupName(groupName);

        if (request.getParameter("btn_submit") != null)
        {
            String interests[] = request.getParameterValues("chk_language");

            for(int i = 0; i < interests.length; i++)
            {
                ArrayList<String> interestList = interests[i];
            }

            User currentUser = Query.userByUserID("userID");
            currentUser.setInterests(interestList);
        }

        try {
            Query.addGroup(newGroup);
        } catch (UserNotFound e){
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter().println("<h1>Group not found!</h1>");
        }

        // redirect
    }

    public void destroy() {

    }
}
