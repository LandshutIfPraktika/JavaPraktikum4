package de.hawlandshut.sgheldd.prak4;

/**
 * Created by s-gheldd on 5/17/15.
 */
public class STORAGE_OCCUPIED_EXCEPTION extends Exception {
    public STORAGE_OCCUPIED_EXCEPTION(){
        super();
    }

    public STORAGE_OCCUPIED_EXCEPTION(String message) {
        super(message);
    }

    public STORAGE_OCCUPIED_EXCEPTION(String message, Throwable throwable) {
        super(message, throwable);
    }


}
