package stockquery.demo.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import stockquery.demo.dto.request.CompanyFilter;
import stockquery.demo.dto.response.PageResult;
import stockquery.demo.dto.response.SectorPerfomance;
import stockquery.demo.repository.CompaniesRepository;
import stockquery.demo.repository.entity.Company;
import stockquery.demo.repository.jpa.command.JpaCompaniesCommand;
import stockquery.demo.repository.jpa.query.JpaCompaniesQuery;
import stockquery.demo.repository.specification.CompaniesSpecification;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CompaniesRepositoryImpl implements CompaniesRepository {

    private final JpaCompaniesQuery jpaQuery;
    private final JpaCompaniesCommand jpaCommand;

    @Override
    public PageResult<Company> getListByFilter(PageRequest pageRequest, CompanyFilter filter) {
        Specification<Company> spec = CompaniesSpecification.hasFilter(
            filter.ticker(),
            filter.companyName(),
            filter.exchange(),
            filter.sector(),
            filter.industry()
        );
        Page<Company> page = jpaQuery.findAll(spec, pageRequest);
        return new PageResult<>(
            page.getContent(), 
            page.getNumber(), 
            page.getSize(), 
            page.getTotalElements(), 
            page.getTotalPages());

    }

    @Override
    public PageResult<SectorPerfomance> getSectorPerformance(PageRequest pageRequest) {
        Page<SectorPerfomance> page = jpaQuery.findSectorPerformance(pageRequest);
        return new PageResult<>(
            page.getContent(), 
            page.getNumber(), 
            page.getSize(), 
            page.getTotalElements(), 
            page.getTotalPages());
    }

    @Override
    public boolean isTickerExists(String ticker) {
        return jpaQuery.existsByTicker(ticker);
    }

    @Override
    public void save(Company company) {
         jpaCommand.save(company);
    }

}
