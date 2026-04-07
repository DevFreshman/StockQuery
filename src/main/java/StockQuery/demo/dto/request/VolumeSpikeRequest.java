package StockQuery.demo.dto.request;

import java.time.LocalDate;

public record VolumeSpikeRequest(
    LocalDate date,
    int baselineDays,
    double spikeThreshold
) {

}
