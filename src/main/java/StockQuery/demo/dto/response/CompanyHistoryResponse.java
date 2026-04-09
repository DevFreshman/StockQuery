package stockquery.demo.dto.response;

import java.time.LocalDate;

public record CompanyHistoryResponse(
    String ticker,
    LocalDate tradeDate,
    String openPrice,
    String highestPrice,
    String lowestPrice,
    String closePrice,
    String volume
) {

}
