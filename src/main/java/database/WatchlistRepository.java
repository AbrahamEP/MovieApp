package database;

import com.mycompany.movieapp.model.Movie;
import java.util.Collections;
import java.util.List;

/**
 * Database operations for the movie watchlist.
 *
 * This is a small educational repository, not a full enterprise architecture.
 * It keeps database code out of Swing while still letting students see the
 * complete JDBC flow in one approachable class.
 */
public class WatchlistRepository {

    private final DatabaseManager databaseManager;
    private final MovieMapper mapper;

    public WatchlistRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.mapper = new MovieMapper();
    }

    public void createTableIfNeeded() {
        /*
         * STUDENT EXERCISE
         *
         * Create the watchlist table using SQLQueries.CREATE_TABLE.
         *
         * Practice:
         * - Getting a Connection from DatabaseManager
         * - Creating a PreparedStatement
         * - Executing schema SQL with executeUpdate()
         * - Closing JDBC resources with try-with-resources
         *
         * Why it matters:
         * The rest of the application depends on the table existing before
         * students try to save or load movies.
         */
    }

    public boolean save(Movie movie) {
        /*
         * STUDENT EXERCISE
         *
         * Write the save operation for one Movie using SQLQueries.INSERT_MOVIE.
         *
         * Practice:
         * - PreparedStatement
         * - Parameter binding
         * - executeUpdate()
         * - Returning true when one row was inserted
         *
         * Think about:
         * - Which Movie getter matches each SQL placeholder?
         * - How should duplicate movie ids be handled?
         * - Why should this method reject a null movie before opening a connection?
         */
        return false;
    }

    public List<Movie> findAll() {
        /*
         * STUDENT EXERCISE
         *
         * Retrieve every Movie stored in SQLite using SQLQueries.FIND_ALL.
         *
         * Practice:
         * - Executing a SELECT query
         * - Iterating through a ResultSet
         * - Calling mapper.fromResultSet(resultSet)
         * - Returning a List<Movie>
         *
         * Why it matters:
         * This method powers the Watchlist option in the Swing combo box.
         */
        return Collections.emptyList();
    }

    public Movie findById(int id) {
        /*
         * STUDENT EXERCISE
         *
         * Retrieve one Movie by id using SQLQueries.FIND_BY_ID.
         *
         * Practice:
         * - Binding one id parameter
         * - Checking ResultSet#next()
         * - Mapping exactly one row
         * - Returning null when the movie is not found
         *
         * Discussion point:
         * Later modules can compare null with Optional, but keep this phase
         * focused on JDBC fundamentals first.
         */
        return null;
    }

    public boolean exists(int id) {
        /*
         * STUDENT EXERCISE
         *
         * Check whether a movie is already saved using SQLQueries.EXISTS.
         *
         * Practice:
         * - SELECT queries for existence checks
         * - PreparedStatement#setInt()
         * - ResultSet#next()
         * - Returning a boolean from database state
         *
         * Why it matters:
         * The Movie Details dialog uses this result to decide whether the Add
         * button should be enabled.
         */
        return false;
    }

    public boolean delete(int id) {
        /*
         * STUDENT EXERCISE
         *
         * Delete one Movie using SQLQueries.DELETE_MOVIE.
         *
         * Practice:
         * - DELETE statements
         * - WHERE clauses
         * - executeUpdate()
         * - Returning true only when a row was deleted
         *
         * Debugging hint:
         * If this returns false, inspect the selected JTable row and the Movie id
         * passed into this method.
         */
        return false;
    }
}
