package database;

/**
 * SQL statements used by WatchlistRepository.
 *
 * The constants are intentionally empty in Phase 1. Students will fill them in
 * while learning how each CRUD operation talks to SQLite.
 */
public final class SQLQueries {

    public static final String CREATE_TABLE = "";
    /*
     * STUDENT EXERCISE
     *
     * Write a CREATE TABLE IF NOT EXISTS statement for the watchlist.
     *
     * Practice:
     * - Choosing SQLite column types
     * - Defining a primary key
     * - Matching table columns to the Movie model
     *
     * Think about:
     * - Why should the TMDB movie id be the primary key?
     * - Which fields should allow null values?
     */

    public static final String INSERT_MOVIE = "";
    /*
     * STUDENT EXERCISE
     *
     * Write an INSERT statement using placeholders (?).
     *
     * Practice:
     * - Column order
     * - PreparedStatement parameters
     * - Preventing SQL injection
     *
     * Think about:
     * - How many placeholders are needed?
     * - How will WatchlistRepository bind each Movie property?
     */

    public static final String FIND_ALL = "";
    /*
     * STUDENT EXERCISE
     *
     * Write a SELECT statement that retrieves every movie in the watchlist.
     *
     * Practice:
     * - SELECT column lists
     * - Reading multiple rows with ResultSet
     * - Choosing an ORDER BY clause when the UI needs predictable results
     */

    public static final String FIND_BY_ID = "";
    /*
     * STUDENT EXERCISE
     *
     * Write a SELECT statement that retrieves one movie by id.
     *
     * Practice:
     * - WHERE clauses
     * - PreparedStatement parameter binding
     * - Handling the case where no row exists
     */

    public static final String EXISTS = "";
    /*
     * STUDENT EXERCISE
     *
     * Write a small SELECT query that answers whether a movie id exists.
     *
     * Practice:
     * - Efficient lookup queries
     * - ResultSet#next()
     * - Returning boolean values from database checks
     */

    public static final String DELETE_MOVIE = "";
    /*
     * STUDENT EXERCISE
     *
     * Write a DELETE statement for one movie id.
     *
     * Practice:
     * - DELETE with a WHERE clause
     * - Binding the selected movie id
     * - Using executeUpdate() to confirm a row was removed
     */

    private SQLQueries() {
    }
}
