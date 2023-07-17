package pr.iceworld.mockspring6ioc.simulatespringioc.exception;

public class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException(String message) {
        super(message);
    }

    public CustomRuntimeException(Throwable cause) {
        super(cause);
    }
}
