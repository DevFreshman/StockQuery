package stockquery.demo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import stockquery.demo.dto.request.VolumeSpikeRequest;
import stockquery.demo.dto.response.PageResult;
import stockquery.demo.dto.response.VolumeSpikeResponse;
import stockquery.demo.repository.StockPriceHistoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VolumeAnalysisService {

    private final StockPriceHistoryRepository repository;

    public PageResult<VolumeSpikeResponse> findVolumeSpikes(PageRequest pageRequest, VolumeSpikeRequest request) {
        return repository.getVolumeSpikes(pageRequest, request);
    }
}
