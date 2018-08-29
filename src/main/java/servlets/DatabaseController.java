package servlets;

import entity.FileRecord;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public abstract class DatabaseController extends HttpServlet {
    String str;
    String file_id;
    String description;
    Part filePart;
    String fileName;
    InputStream is;


    void loadFile(HttpServletRequest req) throws ServletException, IOException{

        str = req.getLocalName();
        file_id = req.getParameter("file_id");
        description = req.getParameter("description");
        filePart = req.getPart("file");
        is = filePart.getInputStream();
        fileName = getSubmittedFileName(filePart);
    }

    //        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//
//        File file = new File(req.getSession().getServletContext().getRealPath("/") + "/" + fileName); // Для Tomcat 8

     String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    void showAllRecords(HttpServletRequest req){

        List<FileRecord> records = new FileRecord().readAll();
        req.setAttribute("size", records.size());
        req.setAttribute("records", records);
    }
}
