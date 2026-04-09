package stockquery.demo.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import stockquery.demo.repository.entity.StockPriceHistory;

public interface JpaStockPriceHistoryCommand extends JpaRepository<StockPriceHistory, Integer> {
    
}
