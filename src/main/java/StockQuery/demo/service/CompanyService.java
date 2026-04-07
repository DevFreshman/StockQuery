package StockQuery.demo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import StockQuery.demo.dto.request.CompanyFilter;
import StockQuery.demo.dto.response.PageResult;
import StockQuery.demo.repository.CompaniesRepository;
import StockQuery.demo.repository.entity.Company;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompanyService {
    private final CompaniesRepository repository;

    public PageResult<Company> findCompanies(PageRequest pageRequest, CompanyFilter filter) {
        return repository.getListByFilter(pageRequest, filter);
    }
}
