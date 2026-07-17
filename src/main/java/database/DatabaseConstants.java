package database;

/**
 * Shared names for the SQLite watchlist table.
 *
 * The goal of this class is to make database code easier to read without
 * hiding SQL from students. They should still understand every table and column
 * name used by the SQL statements.
 */
public final class DatabaseConstants {

    public static final String TABLE_MOVIES = "watchlist";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";

    /*
     * STUDENT EXERCISE
     *
     * Add the remaining column names used by the Movie table.
     *
     * Practice:
     * - Reading the Movie model
     * - Deciding which Movie properties must be persisted
     * - Keeping Java constants synchronized with SQL column names
     *
     * Think about:
     * - Which column stores the rating?
     * - Which column stores the release date?
     * - Which columns are needed to show the details dialog after loading from SQLite?
     */

    private DatabaseConstants() {
    }
}
