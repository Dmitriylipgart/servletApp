package servlets;

import entity.FileRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig()
@WebServlet(value = "/delete")
public class DeleteFromDB extends DatabaseController{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        loadFile(req);
        FileRecord fileRecord = new FileRecord(file_id, fileName, path);
        fileRecord.delete();
        showAllRecords(req);
        getServletContext().getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
    }
}
