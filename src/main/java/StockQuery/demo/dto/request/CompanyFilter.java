package stockquery.demo.dto.request;

import jakarta.validation.constraints.Size;

public record CompanyFilter(
        @Size(min = 1, max = 10, message = "Ticker must be between 1 and 10 characters")
        String ticker,
        @Size(min = 1, max = 255, message = "Company name must be between 1 and 255 characters")
        String companyName,
        @Size(min = 1, max = 10, message = "Exchange must be between 1 and 10 characters")
        String exchange,
        @Size(min = 1, max = 50, message = "Sector must be between 1 and 50 characters")
        String sector,
        @Size(min = 1, max = 50, message = "Industry must be between 1 and 50 characters")
        String industry
) {

}
