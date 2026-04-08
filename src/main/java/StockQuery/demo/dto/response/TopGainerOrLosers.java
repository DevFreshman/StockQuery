package stockquery.demo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TopGainerOrLosers(
    String ticker,
    LocalDate latestDate,
    BigDecimal latestClose,
    LocalDate prevDate,
    BigDecimal prevClose,
    BigDecimal priceChange,
    BigDecimal pctChange
) {
}
