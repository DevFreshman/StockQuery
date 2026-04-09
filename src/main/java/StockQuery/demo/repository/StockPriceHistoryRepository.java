package stockquery.demo.repository;

import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;

import stockquery.demo.dto.request.StockPriceHistoryFilter;
import stockquery.demo.dto.request.VolumeSpikeRequest;
import stockquery.demo.dto.response.PageResult;
import stockquery.demo.dto.response.TopGainerOrLosers;
import stockquery.demo.dto.response.VolumeSpikeResponse;
import stockquery.demo.repository.entity.StockPriceHistory;

public interface StockPriceHistoryRepository {
    PageResult<StockPriceHistory> getListByFilter(PageRequest pageRequest, StockPriceHistoryFilter filter);

    PageResult<TopGainerOrLosers> getTopGainers(PageRequest pageRequest);

    PageResult<TopGainerOrLosers> getTopLosers(PageRequest pageRequest);

    PageResult<VolumeSpikeResponse> getVolumeSpikes(PageRequest pageRequest, VolumeSpikeRequest request);

    StockPriceHistory save(StockPriceHistory stockPriceHistory);

    boolean isHistoryExists(String ticker, LocalDate tradeDate);
}
