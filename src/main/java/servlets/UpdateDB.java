package servlets;


import entity.FileRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@MultipartConfig()
@WebServlet(value = "/jsp/update")
public class UpdateDB extends DatabaseController{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        loadFile(req);
        FileRecord fileRecord = new FileRecord(file_id, description, is);
        fileRecord.update();
        getServletContext().getRequestDispatcher("/jsp/example.jsp").forward(req,resp);
    }
}
