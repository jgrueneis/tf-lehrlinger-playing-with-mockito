package at.twinformatics.lehrlinge.jdbc;

import at.twinformatics.lehrlinge.jdbc.Student;
import at.twinformatics.lehrlinge.jdbc.StudentSelectFromDatabase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudentSelectFromDatabaseMockitoTest {

    @Test
    void verify_selectAll() {
        // Given
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            connection = Mockito.mock(Connection.class);
            preparedStatement = Mockito.mock(PreparedStatement.class);
            resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
            Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
            Mockito.when(resultSet.next()).thenReturn(false);
        }
        catch (SQLException e) {
            throw new RuntimeException("Failed at preparing mocks", e);
        }

        // When
        StudentSelectFromDatabase studentSelectFromDatabase = new StudentSelectFromDatabase(connection);
        List<Student> students = studentSelectFromDatabase.selectAll();

        // Then
        assertThat(students).isNotNull().isEmpty();
    }
}
