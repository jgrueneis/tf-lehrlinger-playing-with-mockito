package at.twinformatics.lehrlinge.jdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StudentSelectFromDatabaseMockitoTest {
    @Mock
    Connection connection;
    @Mock
    PreparedStatement preparedStatement;
    @Mock
    ResultSet resultSet;

    @Test
    void verify_selectAll_NoRows() {
        // Given
        try {
            Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
            Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
            Mockito.when(resultSet.next()).thenReturn(false);
        } catch (SQLException e) {
            throw new RuntimeException("Failed at preparing mocks", e);
        }

        // When
        StudentSelectFromDatabase studentSelectFromDatabase = new StudentSelectFromDatabase(connection);
        List<Student> students = studentSelectFromDatabase.selectAll();

        // Then
        assertThat(students).isNotNull().isEmpty();
    }
}
