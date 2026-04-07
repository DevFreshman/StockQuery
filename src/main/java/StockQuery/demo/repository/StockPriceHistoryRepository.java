package StockQuery.demo.repository;

import org.springframework.data.domain.PageRequest;

import StockQuery.demo.dto.request.StockPriceHistoryFilter;
import StockQuery.demo.dto.request.VolumeSpikeRequest;
import StockQuery.demo.dto.response.PageResult;
import StockQuery.demo.dto.response.TopGainerOrLosers;
import StockQuery.demo.dto.response.VolumeSpikeResponse;
import StockQuery.demo.repository.entity.StockPriceHistory;

public interface StockPriceHistoryRepository {
    PageResult<StockPriceHistory> getListByFilter(PageRequest pageRequest, StockPriceHistoryFilter filter);

    PageResult<TopGainerOrLosers> getTopGainers(PageRequest pageRequest);

    PageResult<TopGainerOrLosers> getTopLosers(PageRequest pageRequest);

    PageResult<VolumeSpikeResponse> getVolumeSpikes(PageRequest pageRequest, VolumeSpikeRequest request);
}
