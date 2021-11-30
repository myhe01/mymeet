import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet(
        name = "searchgroup",
        urlPatterns = "/searchgroup"
)
public class SearchGroupServlet extends HttpServlet {

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
        Integer groupID = request.getParameter("groupID");
        Group searchedGroupID = null;
        try {
            searchedGroupID = Query.groupByGroupID(groupID);
            Integer searchedGroupNameID = Query.groupByGroupName(groupName);
            
            // hopefully redirects
            request.setAttribute("groupID","groupID");
            request.getRequestDispatcher("GroupPage.jsp").forward(request, response);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void destroy() {

    }
}
