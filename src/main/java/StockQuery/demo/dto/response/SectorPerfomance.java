package StockQuery.demo.dto.response;

import java.math.BigDecimal;

public record SectorPerfomance(
    String sector,
    Long numTickers,
    Long numDaysCalculated,
    BigDecimal avgDailyPctChange,
    BigDecimal minPctChange,
    BigDecimal maxPctChange
) {

}
