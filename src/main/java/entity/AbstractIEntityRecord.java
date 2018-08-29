package entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractIEntityRecord<T extends IEntityRecord> implements IEntityRecord<T> {

    DatabaseService databaseService;
    Statement statement;
    Connection connection;

    public AbstractIEntityRecord() {
        databaseService= new DatabaseService();
        statement= databaseService.getStatement();
        connection = databaseService.getConnection();
    }

    @Override
    public void delete() {

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        databaseService.closeConnection();
    }


    protected  ResultSet selectAll()
    {
        try {
            return   statement.executeQuery("select * from "+getTableName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected ResultSet selectById(String id){
        try {
            return   statement.executeQuery("select * from "+getTableName() + " where file_id=" +id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
