package database;

import com.mycompany.movieapp.model.Movie;
import java.sql.ResultSet;

/**
 * Converts database rows into Movie objects.
 *
 * This class exists so students can focus on one of the most important JDBC
 * skills: reading typed values from a ResultSet and building a domain object.
 */
public class MovieMapper {

    public Movie fromResultSet(ResultSet resultSet) {
        /*
         * STUDENT EXERCISE
         *
         * Read every column from the ResultSet and build a Movie object.
         *
         * Practice:
         * - getInt()
         * - getString()
         * - getDouble()
         * - Matching SQL columns to Movie fields
         * - Understanding object mapping
         *
         * Think about:
         * - Which database column maps to Movie#getId()?
         * - Which ResultSet getter matches SQLite INTEGER, REAL, and TEXT values?
         * - Why should mapper code use the same column names as SQLQueries?
         */
        return null;
    }
}
