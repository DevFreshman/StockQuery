package stockquery.demo.dto.request;

import jakarta.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record VolumeSpikeRequest(
        @NotNull
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date,

        @Positive(message = "Baseline days must be a positive number")
        @Min(value = 24, message = "Baseline days must be at least 10")
        @Max(value = 72, message = "Baseline days must be at most 72")
        int baselineDays,

        @NotNull
        @Positive(message = "Spike threshold must be a positive number")
        @Digits(integer = 3, fraction = 2, message = "Spike threshold must be a valid number with up to 3 digits and 2 decimal places")
        double spikeThreshold
) {

}
