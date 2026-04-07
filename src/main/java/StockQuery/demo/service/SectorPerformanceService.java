package StockQuery.demo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import StockQuery.demo.dto.response.PageResult;
import StockQuery.demo.dto.response.SectorPerfomance;
import StockQuery.demo.repository.CompaniesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectorPerformanceService {

    private final CompaniesRepository repository;

    public PageResult<SectorPerfomance> findSectorPerformance(PageRequest pageRequest) {
        return repository.getSectorPerformance(pageRequest);
    }
}

