package StockQuery.demo.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import StockQuery.demo.repository.entity.Company;

@Component
public class CompaniesSpecification {

    public static Specification<Company> hasFilter(
        String ticker,
        String companyName,
        String exchange,
        String sector,
        String industry
    ) {
        return Specification.<Company>unrestricted()
        .and(hasTicker(ticker))
        .and(hasCompanyName(companyName))
        .and(hasExchange(exchange))
        .and(hasSector(sector))
        .and(hasIndustry(industry));
    }

    public static Specification<Company> hasTicker(String ticker) {
        return (root, query, criteriaBuilder) 
        -> ticker != null ? criteriaBuilder.equal(root.get("ticker"), ticker) : criteriaBuilder.conjunction();
    }

    public static Specification<Company> hasCompanyName(String companyName) {
        return (root, query, criteriaBuilder) 
        -> companyName != null ? criteriaBuilder.like(root.get("companyName"), "%" + companyName + "%") : criteriaBuilder.conjunction();
    }

    public static Specification<Company> hasExchange(String exchange) {
        return (root, query, criteriaBuilder) 
        -> exchange != null ? criteriaBuilder.equal(root.get("exchange"), exchange) : criteriaBuilder.conjunction();
    }

    public static Specification<Company> hasSector(String sector) {
        return (root, query, criteriaBuilder) 
        -> sector != null ? criteriaBuilder.equal(root.get("sector"), sector) : criteriaBuilder.conjunction();
    }

    public static Specification<Company> hasIndustry(String industry) {
        return (root, query, criteriaBuilder) 
        -> industry != null ? criteriaBuilder.equal(root.get("industry"), industry) : criteriaBuilder.conjunction();
    }
}
