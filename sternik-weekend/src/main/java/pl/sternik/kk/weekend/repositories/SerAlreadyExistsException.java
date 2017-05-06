package pl.sternik.kk.weekend.repositories;

public class SerAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public SerAlreadyExistsException() {     
    }

    public SerAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerAlreadyExistsException(String message) {
        super(message);
    }

    public SerAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    
}
