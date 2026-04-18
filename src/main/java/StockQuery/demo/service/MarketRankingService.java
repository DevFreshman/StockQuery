package stockquery.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import stockquery.demo.dto.response.PageResult;
import stockquery.demo.dto.response.TopGainerOrLosers;
import stockquery.demo.repository.StockPriceHistoryRepository;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketRankingService {

    private final StockPriceHistoryRepository repository;

    public PageResult<TopGainerOrLosers> findTopGainers(PageRequest pageRequest) {
        log.info("findTopGainers called with pageRequest: {}", pageRequest);
        return repository.getTopGainers(pageRequest);
    }

    public PageResult<TopGainerOrLosers> findTopLosers(PageRequest pageRequest) {
        log.info("findTopLosers called with pageRequest: {}", pageRequest);
        return repository.getTopLosers(pageRequest);
    }
}
