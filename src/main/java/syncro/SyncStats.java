/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syncro;

/**
 *
 * @author abrahamescamillapinelo
 */
public class SyncStats {
    private int downloaded;
    private int inserted;
    private int updated;
    private int skipped;
    private int failed;
    
    public SyncStats(int downloaded) {
        this.downloaded = downloaded;
    }
    
    public boolean hasFailures() {
        return getFailed() > 0;
    }

    /**
     * @return the downloaded
     */
    public int getDownloaded() {
        return downloaded;
    }

    /**
     * @param downloaded the downloaded to set
     */
    public void setDownloaded(int downloaded) {
        this.downloaded = downloaded;
    }

    /**
     * @return the inserted
     */
    public int getInserted() {
        return inserted;
    }

    /**
     * @param inserted the inserted to set
     */
    public void setInserted(int inserted) {
        this.inserted = inserted;
    }

    /**
     * @return the updated
     */
    public int getUpdated() {
        return updated;
    }

    /**
     * @param updated the updated to set
     */
    public void setUpdated(int updated) {
        this.updated = updated;
    }

    /**
     * @return the skipped
     */
    public int getSkipped() {
        return skipped;
    }

    /**
     * @param skipped the skipped to set
     */
    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    /**
     * @return the failed
     */
    public int getFailed() {
        return failed;
    }

    /**
     * @param failed the failed to set
     */
    public void setFailed(int failed) {
        this.failed = failed;
    }
}
