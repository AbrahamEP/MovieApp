package database;

import java.sql.Connection;

/**
 * Creates JDBC connections for the SQLite database.
 *
 * Keep this class focused. It should not create tables, run queries, map
 * ResultSet rows, or know anything about Swing.
 */
public class DatabaseManager {

    /*
     * STUDENT EXERCISE
     *
     * Replace the empty string with the SQLite JDBC URL for this project.
     *
     * Practice:
     * - SQLite connection URL format
     * - Understanding where the database file is stored
     * - Keeping connection settings in one place
     *
     * Hint:
     * The original project stored its database in a file named movieapp.db.
     */
    private static final String DATABASE_URL = "";

    public Connection getConnection() {
        /*
         * STUDENT EXERCISE
         *
         * Create and return a JDBC connection using DriverManager.
         *
         * Practice:
         * - Calling DriverManager.getConnection(DATABASE_URL)
         * - Deciding where SQLException should be handled
         * - Understanding why opening connections is centralized
         *
         * Think about:
         * - Should this method catch SQLException or declare it?
         * - What happens if DATABASE_URL is empty or malformed?
         */
        return null;
    }
}
