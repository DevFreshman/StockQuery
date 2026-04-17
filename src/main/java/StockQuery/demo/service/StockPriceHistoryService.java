package stockquery.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import stockquery.demo.dto.request.StockPriceHistoryFilter;
import stockquery.demo.dto.response.CompanyHistoryResponse;
import stockquery.demo.dto.response.PageResult;
import stockquery.demo.exception.HistoryIsExistsException;
import stockquery.demo.repository.StockPriceHistoryRepository;
import stockquery.demo.repository.entity.StockPriceHistory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StockPriceHistoryService {
    private final StockPriceHistoryRepository repository;

    public PageResult<StockPriceHistory> findHistory(PageRequest pageRequest, StockPriceHistoryFilter filter) {
        return repository.getListByFilter(pageRequest, filter);
    }


    @Transactional
    public CompanyHistoryResponse importTransactionHistory(
            String ticker,
            LocalDate tradeDate,
            BigDecimal openPrice,
            BigDecimal highestPrice,
            BigDecimal lowestPrice,
            BigDecimal closePrice,
            BigDecimal volume
    ) {
        if (repository.isHistoryExists(ticker, tradeDate)) {
            throw new HistoryIsExistsException("history of ticker " + ticker + " on date " + tradeDate + " already exists", ticker, tradeDate);
        }

        StockPriceHistory stockPriceHistory = StockPriceHistory.builder()
                .ticker(ticker)
                .tradeDate(tradeDate)
                .openPrice(openPrice)
                .highestPrice(highestPrice)
                .lowestPrice(lowestPrice)
                .closePrice(closePrice)
                .volume(volume)
                .build();

        repository.save(stockPriceHistory);
        return new CompanyHistoryResponse(
                stockPriceHistory.getTicker(),
                stockPriceHistory.getTradeDate(),
                stockPriceHistory.getOpenPrice().toString(),
                stockPriceHistory.getHighestPrice().toString(),
                stockPriceHistory.getLowestPrice().toString(),
                stockPriceHistory.getClosePrice().toString(),
                stockPriceHistory.getVolume().toString()
        );
    }
}
