package tf.lehrlinge.jdbc.fw;

public class DatabaseException extends RuntimeException {
    private DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public static DatabaseException createDatabaseException(String message, Throwable cause) {
        return new DatabaseException(message, cause);
    }
}
