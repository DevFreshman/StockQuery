package stockquery.demo.exception;

public class HistoryIsExistsException extends RuntimeException {
    public HistoryIsExistsException(String message) {
        super(message);
    }

}
