package stockquery.demo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import stockquery.demo.dto.request.StockPriceHistoryFilter;
import stockquery.demo.dto.response.PageResult;
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
}
