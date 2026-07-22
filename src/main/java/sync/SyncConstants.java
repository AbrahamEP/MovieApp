package sync;

/**
 * Shared constants for the synchronization exercises.
 */
public final class SyncConstants {

    public static final int CACHE_EXPIRATION_HOURS = 0;
    /*
     * EJERCICIO
     *
     * Define cuantas horas puede considerarse vigente una copia local antes de
     * volver a sincronizar.
     *
     * Piensa en:
     * - Que tan seguido cambian los datos remotos
     * - Como afectaria esto al uso de la API
     * - Que experiencia tendria el usuario si trabaja sin conexion
     */

    public static final int DEFAULT_BATCH_SIZE = 0;
    /*
     * EJERCICIO
     *
     * Define un tamano de lote razonable para procesar peliculas durante una
     * sincronizacion.
     *
     * Pregunta:
     * - Por que podria ser mala idea intentar procesar miles de registros sin
     *   dividir el trabajo?
     */

    public static final String DEFAULT_SYNC_MESSAGE = "Synchronization exercise not implemented yet.";

    private SyncConstants() {
    }
}
