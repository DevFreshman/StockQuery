package stockquery.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import stockquery.demo.dto.request.CompanyFilter;
import stockquery.demo.dto.response.CompanyResponse;
import stockquery.demo.dto.response.PageResult;
import stockquery.demo.exception.TickerIsExistsException;
import stockquery.demo.repository.CompaniesRepository;
import stockquery.demo.repository.entity.Company;
import lombok.RequiredArgsConstructor;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompanyService {
    private final CompaniesRepository repository;

    public PageResult<Company> findCompanies(PageRequest pageRequest, CompanyFilter filter) {
        log.info("findCompanies: filter={}", filter);
        return repository.getListByFilter(pageRequest, filter);
    }


    @Transactional
    public CompanyResponse importTicker(
        String ticker,
        String name,
        String exchange,
        String sector,
        String industry
    ) {
        log.info("importTicker: ticker={}, name={}, exchange={}, sector={}, industry={}", ticker, name, exchange, sector, industry);
        if (repository.isTickerExists(ticker)) {
            throw new TickerIsExistsException("Ticker " + ticker + " already exists", ticker);
        }

        Company company = Company.builder()
            .ticker(ticker)
            .companyName(name)
            .exchange(exchange)
            .sector(sector)
            .industry(industry)
            .build();
        repository.save(company);
        log.info("Ticker imported successfully: ticker={}", ticker);
        return new CompanyResponse(
            company.getTicker(),
            company.getCompanyName(),
            company.getExchange(),
            company.getSector(),
            company.getIndustry()
        );
    }
}
