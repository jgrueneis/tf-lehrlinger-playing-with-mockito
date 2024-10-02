package at.twinformatics.lehrlinge.jdbc;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudentSelectFromDatabaseTest {

    private static Connection connection;

    @BeforeAll
    static void initializeDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Statement statement = connection.createStatement();
        statement.execute("""
                CREATE TABLE STUDENTS (
                    ID INT PRIMARY KEY, 
                    FIRST_NAME VARCHAR(50), 
                    LAST_NAME VARCHAR(50), 
                    DATE_OF_BIRTH DATE
                )
                """);
    }

    @Test
    void verify_selectAll_NoRowsFound() {
        StudentSelectFromDatabase studentSelectFromDatabase = new StudentSelectFromDatabase(connection);
        List<Student> students = studentSelectFromDatabase.selectAll();
        assertThat(students).isNotNull().isEmpty();
    }
}
