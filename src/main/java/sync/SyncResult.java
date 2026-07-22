package sync;

/**
 * Summary returned after a synchronization attempt.
 */
public class SyncResult {

    private int downloaded;
    private int inserted;
    private int updated;
    private int skipped;
    private boolean success;
    private String message;

    public SyncResult() {
        /*
         * EJERCICIO
         *
         * Decide que valores iniciales debe tener un resultado de sincronizacion.
         *
         * Practica:
         * - Constructores
         * - Estado inicial valido
         * - Diferenciar una sincronizacion exitosa de una incompleta
         */
        this.message = SyncConstants.DEFAULT_SYNC_MESSAGE;
    }

    public SyncResult(int downloaded, int inserted, int updated, int skipped, boolean success, String message) {
        /*
         * EJERCICIO
         *
         * Completa este constructor pensando en las reglas de negocio.
         *
         * Preguntas:
         * - Deberian permitirse numeros negativos?
         * - Que mensaje debe usarse si message viene null o vacio?
         * - Que combinacion de valores representa una sincronizacion exitosa?
         */
        this.downloaded = downloaded;
        this.inserted = inserted;
        this.updated = updated;
        this.skipped = skipped;
        this.success = success;
        this.message = message;
    }

    public int getDownloaded() {
        return downloaded;
    }

    public int getInserted() {
        return inserted;
    }

    public int getUpdated() {
        return updated;
    }

    public int getSkipped() {
        return skipped;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String toDisplayMessage() {
        /*
         * EJERCICIO
         *
         * Construye un mensaje amigable para Swing usando los contadores del
         * resultado.
         *
         * Practica:
         * - Formatear informacion para la interfaz
         * - Separar datos de presentacion
         * - Comunicar resultados de una operacion al usuario
         */
        return message;
    }
}
