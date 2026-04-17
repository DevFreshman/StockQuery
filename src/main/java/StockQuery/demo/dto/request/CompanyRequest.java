package stockquery.demo.dto.request;

import jakarta.validation.constraints.NotBlank;

/*
    * CompanyRequest is a record that represents the request body for creating a company.
 */
public record CompanyRequest(
    @NotBlank String ticker,
    @NotBlank String companyName,
    @NotBlank String exchange,
    @NotBlank String sector,
    @NotBlank String industry
) {

}