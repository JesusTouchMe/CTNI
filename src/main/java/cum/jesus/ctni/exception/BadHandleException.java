package cum.jesus.ctni.exception;

/**
 * Thrown when a bad handle (e.g. a handle that doesn't point to the right data) is passed to a function that takes handles.
 *
 * @author JesusTouchme
 * @since 1.0
 */
public final class BadHandleException extends Exception {
    public BadHandleException() {
        super();
    }

    public BadHandleException(String message) {
        super(message);
    }

    public BadHandleException(Throwable cause) {
        super(cause);
    }

    public BadHandleException(String message, Throwable cause) {
        super(message, cause);
    }
}
