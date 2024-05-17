package integration;

/**
 * An unchecked exception for when a certain database is unavailable.
 */
public class DatabaseUnavailableException extends RuntimeException {
    String databaseName;

    /**
     * Constructor for the DatabaseUnavailableException.
     *
     * @param databaseName The name of the database that is unavailable.
     */
    public DatabaseUnavailableException(String databaseName) {
        super(String.format("A connection could not be established to the database. (%S)", databaseName));
        this.databaseName = databaseName;
    }

    /**
     * Getter for retrieving the name of the database that is unavailable.
     *
     * @return The name of the database.
     */
    public String getDatabaseName() {
        return databaseName;
    }
}
