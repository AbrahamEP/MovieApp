package sync;

/**
 * Resultado resumido de un intento de sincronizacion.
 */
public class SyncResult {

    private final int downloaded;
    private final int inserted;
    private final int updated;
    private final int skipped;
    private final boolean success;
    private final String message;

    public SyncResult(int downloaded, int inserted, int updated, int skipped, boolean success, String message) {
        this.downloaded = downloaded;
        this.inserted = inserted;
        this.updated = updated;
        this.skipped = skipped;
        this.success = success;
        this.message = message;
    }

    public static SyncResult success(int downloaded, int inserted, int updated, int skipped) {
        return new SyncResult(
                downloaded,
                inserted,
                updated,
                skipped,
                true,
                "Sincronizacion completada."
        );
    }

    public static SyncResult failure(String message) {
        return new SyncResult(0, 0, 0, 0, false, message);
    }

    public static SyncResult failure(int downloaded, int inserted, int updated, int skipped, String message) {
        return new SyncResult(downloaded, inserted, updated, skipped, false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String toDisplayMessage() {
        return "%s Descargadas: %d | Insertadas: %d | Actualizadas: %d | Omitidas: %d".formatted(
                message,
                downloaded,
                inserted,
                updated,
                skipped
        );
    }
}
