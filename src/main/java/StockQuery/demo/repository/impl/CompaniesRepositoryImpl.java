package StockQuery.demo.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import StockQuery.demo.dto.request.CompanyFilter;
import StockQuery.demo.dto.response.PageResult;
import StockQuery.demo.dto.response.SectorPerfomance;
import StockQuery.demo.repository.CompaniesRepository;
import StockQuery.demo.repository.entity.Company;
import StockQuery.demo.repository.jpa.query.JpaCompaniesQuery;
import StockQuery.demo.repository.specification.CompaniesSpecification;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CompaniesRepositoryImpl implements CompaniesRepository {

    private final JpaCompaniesQuery jpaQuery;

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

}
