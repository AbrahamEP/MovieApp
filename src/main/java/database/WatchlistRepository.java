package database;

import com.mycompany.movieapp.model.Movie;
import java.util.Collections;
import java.util.List;

/**
 * Operaciones de base de datos para la watchlist de películas.
 *
 * Este es un repositorio educativo pequeño, no una arquitectura empresarial
 * completa. Mantiene el código de base de datos fuera de Swing y, al mismo
 * tiempo, permite que los estudiantes vean el flujo JDBC completo en una clase
 * fácil de entender.
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
         * EJERCICIO PARA ESTUDIANTES
         *
         * Crea la tabla watchlist usando SQLQueries.CREATE_TABLE.
         *
         * Practica:
         * - Obtener una Connection desde DatabaseManager
         * - Crear un PreparedStatement
         * - Ejecutar SQL de esquema con executeUpdate()
         * - Cerrar recursos JDBC con try-with-resources
         *
         * Por qué importa:
         * El resto de la aplicación depende de que la tabla exista antes de que
         * los estudiantes intenten guardar o cargar películas.
         */
    }

    public boolean save(Movie movie) {
        /*
         * EJERCICIO PARA ESTUDIANTES
         *
         * Escribe la operación para guardar una Movie usando SQLQueries.INSERT_MOVIE.
         *
         * Practica:
         * - PreparedStatement
         * - Enlace de parámetros
         * - executeUpdate()
         * - Devolver true cuando una fila fue insertada
         *
         * Piensa en:
         * - ¿Qué getter de Movie corresponde a cada placeholder SQL?
         * - ¿Cómo deberían manejarse ids de películas duplicados?
         * - ¿Por qué este método debería rechazar una movie null antes de abrir una conexión?
         */
        return false;
    }

    public List<Movie> findAll() {
        /*
         * EJERCICIO PARA ESTUDIANTES
         *
         * Recupera todas las Movie guardadas en SQLite usando SQLQueries.FIND_ALL.
         *
         * Practica:
         * - Ejecutar una consulta SELECT
         * - Iterar sobre un ResultSet
         * - Calling mapper.fromResultSet(resultSet)
         * - Devolver una List<Movie>
         *
         * Por qué importa:
         * Este método alimenta la opción Watchlist del combo box de Swing.
         */
        return Collections.emptyList();
    }

    public Movie findById(int id) {
        /*
         * EJERCICIO PARA ESTUDIANTES
         *
         * Recupera una Movie por id usando SQLQueries.FIND_BY_ID.
         *
         * Practica:
         * - Enlazar un parámetro id
         * - Verificar ResultSet#next()
         * - Mapear exactamente una fila
         * - Devolver null cuando la película no se encuentra
         *
         * Punto de discusión:
         * Módulos posteriores pueden comparar null con Optional, pero mantén
         * esta fase enfocada primero en los fundamentos de JDBC.
         */
        return null;
    }

    public boolean exists(int id) {
        /*
         * EJERCICIO PARA ESTUDIANTES
         *
         * Verifica si una película ya está guardada usando SQLQueries.EXISTS.
         *
         * Practica:
         * - Consultas SELECT para verificar existencia
         * - PreparedStatement#setInt()
         * - ResultSet#next()
         * - Devolver un boolean a partir del estado de la base de datos
         *
         * Por qué importa:
         * El diálogo de detalles de película usa este resultado para decidir si
         * el botón Add debe estar habilitado.
         */
        return false;
    }

    public boolean delete(int id) {
        /*
         * EJERCICIO PARA ESTUDIANTES
         *
         * Elimina una Movie usando SQLQueries.DELETE_MOVIE.
         *
         * Practica:
         * - Sentencias DELETE
         * - Cláusulas WHERE
         * - executeUpdate()
         * - Devolver true solamente cuando una fila fue eliminada
         *
         * Pista de depuración:
         * Si esto devuelve false, inspecciona la fila seleccionada del JTable y
         * el id de Movie enviado a este método.
         */
        return false;
    }
}
