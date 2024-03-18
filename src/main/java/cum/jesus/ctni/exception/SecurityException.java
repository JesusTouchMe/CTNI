package cum.jesus.ctni.exception;

/**
 * Thrown whenever there's an error with security for the vm. Primarily used by memory functions in {@link cum.jesus.ctni.IEnvironment}.
 *
 * @author JesusTouchMe
 * @since 1.0
 */
public final class SecurityException extends Exception {
    public SecurityException() {
        super();
    }

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(Throwable cause) {
        super(cause);
    }

    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }
}
