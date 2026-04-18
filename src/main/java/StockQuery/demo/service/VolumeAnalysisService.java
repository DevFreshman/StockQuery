package stockquery.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import stockquery.demo.dto.response.PageResult;
import stockquery.demo.dto.response.VolumeSpikeResponse;
import stockquery.demo.repository.StockPriceHistoryRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Service
public class VolumeAnalysisService {

    private final StockPriceHistoryRepository repository;

    public PageResult<VolumeSpikeResponse> findVolumeSpikes(PageRequest pageRequest, LocalDate date, int baselineDays, double spikeThreshold) {
        log.info("findVolumeSpikes: date={}, baselineDays={}, spikeThreshold={}", date, baselineDays, spikeThreshold);
        return repository.getVolumeSpikes(pageRequest,date, baselineDays, spikeThreshold);
    }
}
