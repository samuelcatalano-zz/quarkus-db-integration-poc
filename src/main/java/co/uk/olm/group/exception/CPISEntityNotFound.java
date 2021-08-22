package co.uk.olm.group.exception;

public class CPISEntityNotFound extends Exception {

    public CPISEntityNotFound() {
        super();
    }

    public CPISEntityNotFound(String message) {
        super(message);
    }

    public CPISEntityNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
