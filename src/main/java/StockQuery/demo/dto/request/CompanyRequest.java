package stockquery.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CompanyRequest(
    @NotBlank @NotEmpty @NotNull String ticker,
    String companyName,
    String exchange,
    String sector,
    String industry
) {

}