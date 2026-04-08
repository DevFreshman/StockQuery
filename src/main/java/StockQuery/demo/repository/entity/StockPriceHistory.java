package stockquery.demo.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hs_stock_prices")
@NoArgsConstructor
@Getter
public class StockPriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hs_stock_prices_seq")
    @SequenceGenerator(name = "hs_stock_prices_seq", sequenceName = "hs_stock_prices_history_id_seq", allocationSize = 1)
    private Integer historyId;

    @Column(name = "ticker", nullable = false)
    private String ticker;
    
    @Column(name = "trade_date")
    private LocalDate tradeDate;

    @Column(name = "open_price")
    private BigDecimal openPrice;

    @Column(name = "highest_price")
    private BigDecimal highestPrice;

    @Column(name = "lowest_price")
    private BigDecimal lowestPrice;

    @Column(name = "close_price")
    private BigDecimal closePrice;

    @Column(name = "volume")
    private BigDecimal volume;
}
