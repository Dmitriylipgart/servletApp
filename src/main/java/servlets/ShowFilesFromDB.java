package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@MultipartConfig()
@WebServlet(value = "/showfilelist")

public class ShowFilesFromDB extends DatabaseController{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showAllRecords(req);
        getServletContext().getRequestDispatcher("/jsp/main.jsp").forward(req,resp);
    }
}
