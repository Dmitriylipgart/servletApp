package entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileRecord extends AbstractIEntityRecord<FileRecord> {


    public static final String TABLE_NAME = "files";

    String file_id;
    File file;
    String description;
    String fileName;

    InputStream is;
    String path;


    public FileRecord() {
    }

    public FileRecord(String description, InputStream is, String fileName, String path) {
        this.file_id = file_id;
        this.description = description;
        this.fileName = fileName;
        this.is = is;
        this.path = path;
    }

    public FileRecord(String file_id) {
        this.file_id = file_id;
    }
    public FileRecord(String file_id, String fileName) {
        this.file_id = file_id;
        this.fileName = fileName;
    }
    public FileRecord(String file_id, String description, InputStream is) {
        this.file_id = file_id;
        this.description = description;
        this.is = is;
    }
    public FileRecord(String description, InputStream is) {
        this.description = description;
        this.is = is;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public Object getFile() {
        return file;
    }

    public void setFile(InputStream is) {
        this.file = writeToFile(is);
    }

    public void setFile(String path) {
        this.file = new File(path);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getTableName() {
        return TABLE_NAME;
    }


    public String getPathForPage() {
        return "/files/" + fileName;
    }


    public void createRecord() {

        String sql = "Insert into files (file_path, description, file_name)" //
                + " values (?,?,?)";

        setFile(is);

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, file.getPath());
            statement.setString(2, description);
            statement.setString(3, fileName);
            statement.executeUpdate();
        } catch (Exception e) {}
    }

    public void update() {
        String sql = "Update " + getTableName() + " set file_path = ?, description = ? where file_id = " + file_id;
        setFile(is);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, file.getPath());
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
            is.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        String sql = "Delete from " + getTableName() + " where file_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, file_id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {}
        new File(path + fileName).delete();
    }

    public List<FileRecord> readAll() {
        ResultSet resultSet = selectAll();
        List<FileRecord> fileRecords = new ArrayList<>();
        try {
            while (resultSet.next()) {
                FileRecord fileRecord = new FileRecord();
                fileRecord.setDescription(resultSet.getString("description"));
                fileRecord.setFile_id(resultSet.getString("file_id"));
                fileRecord.setFileName(resultSet.getString("file_name"));
                fileRecord.setFile(resultSet.getString("file_path"));
                fileRecords.add(fileRecord);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileRecords;
    }

    public FileRecord read() {
        ResultSet resultSet = selectById(file_id);
        FileRecord fileRecord = new FileRecord();

        try {
            fileRecord.setFile_id(resultSet.getString("file_id"));
            fileRecord.setDescription(resultSet.getString("description"));
            fileRecord.setFile(resultSet.getBinaryStream("file"));
            fileRecord.setFileName(resultSet.getString("file_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileRecord;
    }




    public File writeToFile(InputStream is) {

        int i = 0;
        File file = new File(path + fileName);
        while (file.exists()) {
            if (i == 0) {
                fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "(" + ++i + ")" + fileName.substring(fileName.lastIndexOf("."));
            } else {
                fileName = fileName.substring(0, fileName.lastIndexOf("(") + 1) + ++i + ")" + fileName.substring(fileName.lastIndexOf("."));
            }
            file = new File(path + fileName);
        }
        try (FileOutputStream fos = new FileOutputStream(file)){
            byte[] buffer = new byte[1024];
            while (is.read(buffer) > 0) {
                fos.write(buffer);
            }
            fos.flush();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}


