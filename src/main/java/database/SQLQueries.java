package database;

/**
 * Sentencias SQL usadas por WatchlistRepository.
 *
 * Las constantes están intencionalmente vacías en la Fase 1. Los estudiantes
 * las completarán mientras aprenden cómo cada operación CRUD se comunica con SQLite.
 */
public final class SQLQueries {

    public static final String CREATE_TABLE = "";
    /*
     * EJERCICIO PARA ESTUDIANTES
     *
     * Escribe una sentencia CREATE TABLE IF NOT EXISTS para la watchlist.
     *
     * Practica:
     * - Elegir tipos de columna de SQLite
     * - Definir una llave primaria
     * - Relacionar las columnas de la tabla con el modelo Movie
     *
     * Piensa en:
     * - ¿Por qué el id de película de TMDB debería ser la llave primaria?
     * - ¿Qué campos deberían permitir valores null?
     */

    public static final String INSERT_MOVIE = "";
    /*
     * EJERCICIO PARA ESTUDIANTES
     *
     * Escribe una sentencia INSERT usando placeholders (?).
     *
     * Practica:
     * - Orden de columnas
     * - Parámetros de PreparedStatement
     * - Prevención de inyección SQL
     *
     * Piensa en:
     * - ¿Cuántos placeholders se necesitan?
     * - ¿Cómo enlazará WatchlistRepository cada propiedad de Movie?
     */

    public static final String UPDATE_MOVIE = "";
    /*
     * EJERCICIO PARA ESTUDIANTES
     *
     * Escribe una sentencia UPDATE para refrescar los datos locales de una
     * pelicula que ya existe.
     *
     * Practica:
     * - UPDATE con placeholders
     * - Clausula WHERE por id
     * - Decidir que columnas deben cambiar durante sincronizacion
     *
     * Piensa en:
     * - El id identifica la pelicula; normalmente no deberia actualizarse.
     * - Algunos datos remotos, como rating u overview, pueden cambiar con el tiempo.
     */

    public static final String FIND_ALL = "";
    /*
     * EJERCICIO PARA ESTUDIANTES
     *
     * Escribe una sentencia SELECT que recupere todas las películas de la watchlist.
     *
     * Practica:
     * - Listas de columnas en SELECT
     * - Lectura de múltiples filas con ResultSet
     * - Elección de una cláusula ORDER BY cuando la UI necesita resultados predecibles
     */

    public static final String FIND_BY_ID = "";
    /*
     * EJERCICIO PARA ESTUDIANTES
     *
     * Escribe una sentencia SELECT que recupere una película por id.
     *
     * Practica:
     * - Cláusulas WHERE
     * - Enlace de parámetros con PreparedStatement
     * - Manejo del caso donde no existe ninguna fila
     */

    public static final String EXISTS = "";
    /*
     * EJERCICIO PARA ESTUDIANTES
     *
     * Escribe una consulta SELECT pequeña que responda si existe un id de película.
     *
     * Practica:
     * - Consultas de búsqueda eficientes
     * - ResultSet#next()
     * - Devolver valores booleanos desde verificaciones de base de datos
     */

    public static final String DELETE_MOVIE = "";
    /*
     * EJERCICIO PARA ESTUDIANTES
     *
     * Escribe una sentencia DELETE para un id de película.
     *
     * Practica:
     * - DELETE con una cláusula WHERE
     * - Enlazar el id de la película seleccionada
     * - Usar executeUpdate() para confirmar que una fila fue eliminada
     */

    private SQLQueries() {
    }
}
