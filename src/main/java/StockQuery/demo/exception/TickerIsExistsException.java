package stockquery.demo.exception;

public class TickerIsExistsException extends RuntimeException {
    public TickerIsExistsException(String message) {
        super(message);
    }
}
