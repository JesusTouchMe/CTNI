package cum.jesus.ctni.exception;

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
