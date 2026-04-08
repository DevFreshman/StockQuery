package stockquery.demo.repository;

import org.springframework.data.domain.PageRequest;

import stockquery.demo.dto.request.CompanyFilter;
import stockquery.demo.dto.response.PageResult;
import stockquery.demo.dto.response.SectorPerfomance;
import stockquery.demo.repository.entity.Company;

public interface CompaniesRepository {
    PageResult<Company> getListByFilter(PageRequest pageRequest, CompanyFilter filter);

    PageResult<SectorPerfomance> getSectorPerformance(PageRequest pageRequest);

    boolean isTickerExists(String ticker);

    Company save(Company company);
}
