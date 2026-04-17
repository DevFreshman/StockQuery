package stockquery.demo.exception;

public class TickerIsExistsException extends RuntimeException {
    String ticker;
    public TickerIsExistsException(String message, String ticker) {
        super(message);
        this.ticker = ticker;
    }

    public String ticker() {
        return ticker;
    }
}
