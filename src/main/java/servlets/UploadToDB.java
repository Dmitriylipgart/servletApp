package servlets;

import entity.FileRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@MultipartConfig()
@WebServlet(value = "/uploadFile")
public class UploadToDB extends DatabaseController {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        loadFile(req);
        FileRecord fileRecord = new FileRecord(description, is, fileName);
        fileRecord.createRecord();
        req.setAttribute("uploadFileResp", "Файл "+ fileName + " уcпешно записан в базу данных " + fileRecord.TABLE_NAME);
        showAllRecords(req);
        getServletContext().getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
    }

}
