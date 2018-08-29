package entity;

import java.sql.SQLException;
import java.util.List;

public interface IEntityRecord<T extends IEntityRecord>{

    String getTableName();
    void createRecord();
    List<T> readAll() throws SQLException;
    T read();
    void update();
    void delete();
}
