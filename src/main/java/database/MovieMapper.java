package database;

import com.mycompany.movieapp.model.Movie;
import java.sql.ResultSet;

/**
 * Convierte filas de la base de datos en objetos Movie.
 *
 * Esta clase existe para que los estudiantes se enfoquen en una de las
 * habilidades más importantes de JDBC: leer valores tipados desde un ResultSet
 * y construir un objeto de dominio.
 */
public class MovieMapper {

    public Movie fromResultSet(ResultSet resultSet) {
        /*
         * EJERCICIO PARA ESTUDIANTES
         *
         * Lee cada columna del ResultSet y construye un objeto Movie.
         *
         * Practica:
         * - getOptInt()
         * - getOptString()
         * - getOptDouble()
         * - Relacionar columnas SQL con campos de Movie
         * - Entender el mapeo de objetos
         *
         * Piensa en:
         * - ¿Qué columna de la base de datos se mapea con Movie#getId()?
         * - ¿Qué getter de ResultSet corresponde a valores SQLite INTEGER, REAL y TEXT?
         * - ¿Por qué el código del mapper debe usar los mismos nombres de columnas que SQLQueries?
         */
        return null;
    }
}
