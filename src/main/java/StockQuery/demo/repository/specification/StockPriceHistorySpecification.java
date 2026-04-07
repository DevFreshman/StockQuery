package StockQuery.demo.repository.specification;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import StockQuery.demo.repository.entity.StockPriceHistory;


@Component
public class StockPriceHistorySpecification {

    public static Specification<StockPriceHistory> hasFilter(
        String ticker,
        LocalDate tradeDateFrom,
        LocalDate tradeDateTo,
        BigDecimal openPriceGreaterThan,
        BigDecimal openPriceLessThan,
        BigDecimal highestPriceGreaterThan,
        BigDecimal highestPriceLessThan,
        BigDecimal lowestPriceGreaterThan,
        BigDecimal lowestPriceLessThan,
        BigDecimal closePriceGreaterThan,
        BigDecimal closePriceLessThan,
        BigDecimal volumeGreaterThan,
        BigDecimal volumeLessThan
    ){
        return Specification.<StockPriceHistory>unrestricted()
        .and(hasTicker(ticker))
        .and(hasTradeDate(tradeDateFrom, tradeDateTo))
        .and(hasGreaterOpenPrice(openPriceGreaterThan))
        .and(hasLessOpenPrice(openPriceLessThan))
        .and(hasGreaterHighestPrice(highestPriceGreaterThan))
        .and(hasLessHighestPrice(highestPriceLessThan))
        .and(hasGreaterLowestPrice(lowestPriceGreaterThan))
        .and(hasLessLowestPrice(lowestPriceLessThan))
        .and(hasGreaterClosePrice(closePriceGreaterThan))
        .and(hasLessClosePrice(closePriceLessThan))
        .and(hasGreaterVolume(volumeGreaterThan))
        .and(hasLessVolume(volumeLessThan));
    }


    public static Specification<StockPriceHistory> hasTicker(String ticker) {
        return (root, query, criteriaBuilder) 
        -> ticker != null ? criteriaBuilder.equal(root.get("ticker"), ticker) : criteriaBuilder.conjunction();
    }

    public static Specification<StockPriceHistory> hasTradeDate(LocalDate tradeDateFrom, LocalDate tradeDateTo) {
        return (root, query, criteriaBuilder) 
        -> tradeDateFrom != null && tradeDateTo != null ? criteriaBuilder.between(root.get("tradeDate"), tradeDateFrom, tradeDateTo) : criteriaBuilder.conjunction();
    }

    public static Specification<StockPriceHistory> hasGreaterOpenPrice(BigDecimal price) {
        return (root, query, criteriaBuilder) 
        -> price != null ? criteriaBuilder.greaterThan(root.get("openPrice"), price) : criteriaBuilder.conjunction();
    }

    public static Specification<StockPriceHistory> hasLessOpenPrice(BigDecimal price) {
        return (root, query, criteriaBuilder) 
        -> price != null ? criteriaBuilder.lessThan(root.get("openPrice"), price) : criteriaBuilder.conjunction();
    }

    public static Specification<StockPriceHistory> hasGreaterHighestPrice(BigDecimal price) {
        return (root, query, criteriaBuilder) 
        -> price != null ? criteriaBuilder.greaterThan(root.get("highestPrice"), price) : criteriaBuilder.conjunction();
    }

    public static Specification<StockPriceHistory> hasLessHighestPrice(BigDecimal price) {
        return (root, query, criteriaBuilder) 
        -> price != null ? criteriaBuilder.lessThan(root.get("highestPrice"), price) : criteriaBuilder.conjunction();
    }

    public static Specification<StockPriceHistory> hasGreaterLowestPrice(BigDecimal price) {
        return (root, query, criteriaBuilder) 
        -> price != null ? criteriaBuilder.greaterThan(root.get("lowestPrice"), price) : criteriaBuilder.conjunction();
    }

    public static Specification<StockPriceHistory> hasLessLowestPrice(BigDecimal price) {
        return (root, query, criteriaBuilder) 
        -> price != null ? criteriaBuilder.lessThan(root.get("lowestPrice"), price) : criteriaBuilder.conjunction();
    }

    public static Specification<StockPriceHistory> hasGreaterClosePrice(BigDecimal price) {
        return (root, query, criteriaBuilder) 
        -> price != null ? criteriaBuilder.greaterThan(root.get("closePrice"), price) : criteriaBuilder.conjunction();
    }

    public static Specification<StockPriceHistory> hasLessClosePrice(BigDecimal price) {
        return (root, query, criteriaBuilder) 
        -> price != null ? criteriaBuilder.lessThan(root.get("closePrice"), price) : criteriaBuilder.conjunction();
    }

    public static Specification<StockPriceHistory> hasGreaterVolume(BigDecimal volume) {
        return (root, query, criteriaBuilder) 
        -> volume != null ? criteriaBuilder.greaterThan(root.get("volume"), volume) : criteriaBuilder.conjunction();
    }

    public static Specification<StockPriceHistory> hasLessVolume(BigDecimal volume) {
        return (root, query, criteriaBuilder) 
        -> volume != null ? criteriaBuilder.lessThan(root.get("volume"), volume) : criteriaBuilder.conjunction();
    }
}
