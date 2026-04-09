package stockquery.demo.dto.response;

public record CompanyResponse(
    String ticker,
    String companyName,
    String exchange,
    String sector,
    String industry
) {

}
