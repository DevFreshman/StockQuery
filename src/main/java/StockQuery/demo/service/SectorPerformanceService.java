package stockquery.demo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import stockquery.demo.dto.response.PageResult;
import stockquery.demo.dto.response.SectorPerfomance;
import stockquery.demo.repository.CompaniesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectorPerformanceService {

    private final CompaniesRepository repository;

    public PageResult<SectorPerfomance> findSectorPerformance(PageRequest pageRequest) {
        return repository.getSectorPerformance(pageRequest);
    }
}

