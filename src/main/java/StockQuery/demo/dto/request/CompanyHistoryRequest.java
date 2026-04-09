package stockquery.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CompanyHistoryRequest(
        @NotBlank(message = "Ticker not be blank")
        String ticker,

        @NotNull(message = "Trade date not be null")
        LocalDate tradeDate,

        @NotNull(message = "Open price not be null")
        BigDecimal openPrice,

        @NotNull(message = "Highest price not be null")
        BigDecimal highestPrice,

        @NotNull(message = "Lowest price not be null")
        BigDecimal lowestPrice,

        @NotNull(message = "Close price not be null")
        BigDecimal closePrice,

        @NotNull(message = "Volume not be null")
        @PositiveOrZero(message = "Volume must be a positive number")
        BigDecimal volume
) {
}