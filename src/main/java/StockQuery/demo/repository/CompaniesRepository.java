package StockQuery.demo.repository;

import org.springframework.data.domain.PageRequest;

import StockQuery.demo.dto.request.CompanyFilter;
import StockQuery.demo.dto.response.PageResult;
import StockQuery.demo.dto.response.SectorPerfomance;
import StockQuery.demo.repository.entity.Company;

public interface CompaniesRepository {
    PageResult<Company> getListByFilter(PageRequest pageRequest, CompanyFilter filter);

    PageResult<SectorPerfomance> getSectorPerformance(PageRequest pageRequest);
}
