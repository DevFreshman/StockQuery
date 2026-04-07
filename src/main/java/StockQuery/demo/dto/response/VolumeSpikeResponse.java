package StockQuery.demo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VolumeSpikeResponse(
    String ticker,
    LocalDate trade_date,
    BigDecimal volume,
    BigDecimal avgVolume,
    BigDecimal spikeRatio,
    boolean isSpike
) {

}
