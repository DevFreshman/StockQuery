package StockQuery.demo.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public record StockPriceHistoryFilter(
    String ticker,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate tradeDateFrom,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate tradeDateTo,
        
    BigDecimal openPriceGreaterThan,
        
    BigDecimal openPriceLessThan,
        
    BigDecimal highestPriceGreaterThan,

    BigDecimal highestPriceLessThan,
        
    BigDecimal lowestPriceGreaterThan,
       
    BigDecimal lowestPriceLessThan,
        
    BigDecimal closePriceGreaterThan,
        
    BigDecimal closePriceLessThan,
        
    BigDecimal volumeGreaterThan,
        
    BigDecimal volumeLessThan

) {

}
