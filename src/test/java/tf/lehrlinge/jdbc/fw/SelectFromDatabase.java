package tf.lehrlinge.jdbc.fw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SelectFromDatabase<M extends Model> {

    private final Connection connection;

    public SelectFromDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<M> selectAll() {
        ArrayList<M> result = new ArrayList<>();

        try (PreparedStatement selectStatement = connection.prepareStatement(selectAllStatementText())) {
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    M model = extractModelFromResultSet(resultSet);
                    result.add(model);
                }
            }
        } catch (SQLException e) {
            throw DatabaseException.createDatabaseException("Failed to prepare select all statement.", e);
        }

        return result;
    }

    protected abstract String selectAllStatementText();

    protected abstract M extractModelFromResultSet(ResultSet resultSet);
}
