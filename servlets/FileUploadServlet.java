import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 5, 
    maxRequestSize = 1024 * 1024 * 5 * 5
)
public class FileUploadServlet extends HttpServlet {

    String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) uploadDir.mkdir();

    for (Part part : request.getParts()) {
        fileName = getFileName(part);
        part.write(uploadPath + File.separator + fileName);
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        return Constants.DEFAULT_FILENAME;
    }
}
