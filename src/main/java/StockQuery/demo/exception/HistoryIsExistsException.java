package stockquery.demo.exception;

import java.time.LocalDate;

public class HistoryIsExistsException extends RuntimeException {
    final String ticker;
    final LocalDate tradeDate;
    public HistoryIsExistsException(String message, String ticker, LocalDate tradeDate) {
        super(message);
        this.ticker = ticker;
        this.tradeDate = tradeDate;
    }

    public String ticker() {
        return ticker;
    }
    public LocalDate tradeDate() {
        return tradeDate;
    }
}
