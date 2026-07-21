package database;

/**
 * Nombres compartidos para la tabla watchlist de SQLite.
 *
 * El objetivo de esta clase es hacer que el código de base de datos sea más
 * fácil de leer sin ocultar SQL a los estudiantes. Aun así, deben entender
 * cada nombre de tabla y columna usado por las sentencias SQL.
 */
public final class DatabaseConstants {

    public static final String TABLE_MOVIES = "watchlist";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";

    /*
     * EJERCICIO PARA ESTUDIANTES
     *
     * Agrega los nombres de columnas restantes usados por la tabla de películas.
     *
     * Practica:
     * - Leer el modelo Movie
     * - Decidir qué propiedades de Movie deben persistirse
     * - Mantener las constantes de Java sincronizadas con los nombres de columnas SQL
     *
     * Piensa en:
     * - ¿Qué columna guarda la calificación?
     * - ¿Qué columna guarda la fecha de estreno?
     * - ¿Qué columnas se necesitan para mostrar el diálogo de detalles después de cargar desde SQLite?
     */

    private DatabaseConstants() {
    }
}
