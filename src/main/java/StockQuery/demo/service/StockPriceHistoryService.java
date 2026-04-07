package StockQuery.demo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import StockQuery.demo.dto.request.StockPriceHistoryFilter;
import StockQuery.demo.dto.response.PageResult;
import StockQuery.demo.repository.StockPriceHistoryRepository;
import StockQuery.demo.repository.entity.StockPriceHistory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StockPriceHistoryService {
    private final StockPriceHistoryRepository repository;

    public PageResult<StockPriceHistory> findHistory(PageRequest pageRequest, StockPriceHistoryFilter filter) {
        return repository.getListByFilter(pageRequest, filter);
    }
}
