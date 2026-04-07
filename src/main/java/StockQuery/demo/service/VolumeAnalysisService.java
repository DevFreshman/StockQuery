package StockQuery.demo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import StockQuery.demo.dto.request.VolumeSpikeRequest;
import StockQuery.demo.dto.response.PageResult;
import StockQuery.demo.dto.response.VolumeSpikeResponse;
import StockQuery.demo.repository.StockPriceHistoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VolumeAnalysisService {

    private final StockPriceHistoryRepository repository;

    public PageResult<VolumeSpikeResponse> findVolumeSpikes(PageRequest pageRequest, VolumeSpikeRequest request) {
        return repository.getVolumeSpikes(pageRequest, request);
    }
}
