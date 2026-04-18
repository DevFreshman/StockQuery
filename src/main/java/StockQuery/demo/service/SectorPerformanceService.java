package stockquery.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import stockquery.demo.dto.response.PageResult;
import stockquery.demo.dto.response.SectorPerfomance;
import stockquery.demo.repository.CompaniesRepository;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class SectorPerformanceService {

    private final CompaniesRepository repository;

    public PageResult<SectorPerfomance> findSectorPerformance(PageRequest pageRequest) {
        log.info("findSectorPerformance called with pageRequest: {}", pageRequest);
        return repository.getSectorPerformance(pageRequest);
    }
}

