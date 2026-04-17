package stockquery.demo.dto.request;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CompanyHistoryRequest(
        @NotBlank(message = "Ticker not be blank")
        @Size(min = 1, max = 10, message = "Ticker must be between 1 and 10 characters")
        String ticker,

        @NotNull(message = "Trade date not be null")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate tradeDate,

        @NotNull(message = "Open price not be null")
        @Digits(integer = 10, fraction = 2, message = "Open price must be a valid number with up to 10 digits and 2 decimal places")
        @Positive(message = "Open price must be a positive number")
        @DecimalMin(value = "0.5", inclusive = false, message = "Open price must be greater than 0.5")
        BigDecimal openPrice,

        @NotNull(message = "Highest price not be null")
        @Digits(integer = 10, fraction = 2, message = "Highest price must be a valid number with up to 10 digits and 2 decimal places")
        @Positive(message = "Highest price must be a positive number")
        @DecimalMin(value = "0.5", inclusive = false, message = "Highest price must be greater than 0.5")
        BigDecimal highestPrice,

        @NotNull(message = "Lowest price not be null")
        @Digits(integer = 10, fraction = 2, message = "Lowest price must be a valid number with up to 10 digits and 2 decimal places")
        @Positive(message = "Lowest price must be a positive number")
        @DecimalMin(value = "0.5", inclusive = false, message = "Lowest price must be greater than 0.5")
        BigDecimal lowestPrice,

        @NotNull(message = "Close price not be null")
        @Digits(integer = 10, fraction = 2, message = "Close price must be a valid number with up to 10 digits and 2 decimal places")
        @Positive(message = "Close price must be a positive number")
        @DecimalMin(value = "0.5", inclusive = false, message = "Close price must be greater than 0.5")
        BigDecimal closePrice,

        @NotNull(message = "Volume not be null")
        @Digits(integer = 10, fraction = 0, message = "Volume must be a valid number with up to 10 digits")
        @PositiveOrZero(message = "Volume must be a positive number")
        @DecimalMin(value = "0", inclusive = false, message = "Volume must be greater than 0")
        BigDecimal volume
) {
}