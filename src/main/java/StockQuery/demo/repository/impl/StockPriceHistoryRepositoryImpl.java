package stockquery.demo.repository.impl;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import stockquery.demo.dto.request.StockPriceHistoryFilter;
import stockquery.demo.dto.request.VolumeSpikeRequest;
import stockquery.demo.dto.response.PageResult;
import stockquery.demo.dto.response.TopGainerOrLosers;
import stockquery.demo.dto.response.VolumeSpikeResponse;
import stockquery.demo.repository.StockPriceHistoryRepository;
import stockquery.demo.repository.entity.StockPriceHistory;
import stockquery.demo.repository.jpa.JpaStockPriceHistoryCommand;
import stockquery.demo.repository.jpa.query.JpaStockPriceHistoryQuery;
import stockquery.demo.repository.specification.StockPriceHistorySpecification;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Repository
public class StockPriceHistoryRepositoryImpl implements StockPriceHistoryRepository {

    private final JpaStockPriceHistoryQuery jpaStockPriceHistoryQuery;
    private final JpaStockPriceHistoryCommand jpaStockPriceHistoryCommand;

    @Override
    public PageResult<StockPriceHistory> getListByFilter(PageRequest pageRequest, StockPriceHistoryFilter filter) {
        Specification<StockPriceHistory> specification = StockPriceHistorySpecification.hasFilter(
            filter.ticker(),
            filter.tradeDateFrom(),
            filter.tradeDateTo(),
            filter.openPriceGreaterThan(),
            filter.openPriceLessThan(),
            filter.highestPriceGreaterThan(),
            filter.highestPriceLessThan(),
            filter.lowestPriceGreaterThan(),
            filter.lowestPriceLessThan(),
            filter.closePriceGreaterThan(),
            filter.closePriceLessThan(),
            filter.volumeGreaterThan(),
            filter.volumeLessThan()
        );

        Page<StockPriceHistory> page = jpaStockPriceHistoryQuery.findAll(specification, pageRequest);
        
        return new PageResult<StockPriceHistory>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public PageResult<TopGainerOrLosers> getTopGainers(PageRequest pageRequest) {
        Page<TopGainerOrLosers> page = jpaStockPriceHistoryQuery.findTopGainers(pageRequest);
        return new PageResult<TopGainerOrLosers>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public PageResult<TopGainerOrLosers> getTopLosers(PageRequest pageRequest) {
        Page<TopGainerOrLosers> page = jpaStockPriceHistoryQuery.findTopLosers(pageRequest);
        return new PageResult<TopGainerOrLosers>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public PageResult<VolumeSpikeResponse> getVolumeSpikes(PageRequest pageRequest, VolumeSpikeRequest request) {
        Page<VolumeSpikeResponse> page = jpaStockPriceHistoryQuery.findVolumeSpikesByDate(pageRequest, request.date(), request.baselineDays(), request.spikeThreshold());
        return new PageResult<VolumeSpikeResponse>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

    @Override
    public StockPriceHistory save(StockPriceHistory stockPriceHistory) {
        return jpaStockPriceHistoryCommand.save(stockPriceHistory);
    }

    @Override
    public boolean isHistoryExists(String ticker, LocalDate tradeDate) {
        return jpaStockPriceHistoryQuery.existsByTickerAndTradeDate(ticker, tradeDate);
    }

}
