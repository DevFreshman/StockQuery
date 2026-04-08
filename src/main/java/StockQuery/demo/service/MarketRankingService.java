package stockquery.demo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import stockquery.demo.dto.response.PageResult;
import stockquery.demo.dto.response.TopGainerOrLosers;
import stockquery.demo.repository.StockPriceHistoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarketRankingService {

    private final StockPriceHistoryRepository repository;

    public PageResult<TopGainerOrLosers> findTopGainers(PageRequest pageRequest) {
        return repository.getTopGainers(pageRequest);
    }

    public PageResult<TopGainerOrLosers> findTopLosers(PageRequest pageRequest) {
        return repository.getTopLosers(pageRequest);
    }
}
