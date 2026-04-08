package stockquery.demo.dto.request;

public record CompanyFilter(
    String ticker,
    String companyName,
    String exchange,
    String sector,
    String industry
) {

}
