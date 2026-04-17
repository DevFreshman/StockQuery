package stockquery.demo.exception;

import java.time.LocalDate;

public class HistoryIsExistsException extends RuntimeException {
    String ticker;
    LocalDate tradeDate;
    public HistoryIsExistsException(String message, String ticker, LocalDate tradeDate) {
        super(message);
    }

    public String ticker() {
        return ticker;
    }
    public LocalDate tradeDate() {
        return tradeDate;
    }
}
