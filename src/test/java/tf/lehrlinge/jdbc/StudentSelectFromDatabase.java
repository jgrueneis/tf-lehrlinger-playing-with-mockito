package tf.lehrlinge.jdbc;


import tf.lehrlinge.jdbc.fw.DatabaseException;
import tf.lehrlinge.jdbc.fw.SelectFromDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class StudentSelectFromDatabase extends SelectFromDatabase<Student> {

    public StudentSelectFromDatabase(Connection connection) {
        super(connection);
    }

    @Override
    protected String selectAllStatementText() {
        return "SELECT ID, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH FROM STUDENTS";
    }

    @Override
    protected Student extractModelFromResultSet(ResultSet resultSet) {
        try {
            Integer id = resultSet.getInt("ID");
            String firstName = resultSet.getString("FIRST_NAME");
            String lastName = resultSet.getString("LAST_NAME");
            LocalDate dateOfBirth = resultSet.getObject("DATE_OF_BIRTH", LocalDate.class);
            return new Student(id, firstName, lastName, dateOfBirth);
        } catch (SQLException e) {
            throw DatabaseException.createDatabaseException("Failed to extract model from ResultSet", e);
        }
    }
}
