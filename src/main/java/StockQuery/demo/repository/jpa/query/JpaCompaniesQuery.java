package stockquery.demo.repository.jpa.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import stockquery.demo.dto.response.SectorPerfomance;
import stockquery.demo.repository.entity.Company;

@Repository
public interface JpaCompaniesQuery extends JpaRepository<Company, String>,JpaSpecificationExecutor<Company> {


    @Query(
        value = """
        WITH tb_changes AS (
            SELECT
                ticker,
                LAG(close_price) OVER (PARTITION BY ticker ORDER BY trade_date) AS prev_close,
                ROUND(
                    (close_price - LAG(close_price) OVER (PARTITION BY ticker ORDER BY trade_date))
                    / NULLIF(LAG(close_price) OVER (PARTITION BY ticker ORDER BY trade_date), 0) * 100,
                2) AS pct_change
            FROM hs_stock_prices
        )
        SELECT
            c.sector,
            COUNT(DISTINCT tb.ticker)      AS num_tickers,
            COUNT(tb.pct_change)           AS num_days_calculated,
            ROUND(AVG(tb.pct_change), 2)   AS avg_daily_pct_change,
            ROUND(MIN(tb.pct_change), 2)   AS min_pct_change,
            ROUND(MAX(tb.pct_change), 2)   AS max_pct_change
        FROM companies c
        LEFT JOIN tb_changes tb ON c.ticker = tb.ticker
        GROUP BY c.sector
        ORDER BY avg_daily_pct_change DESC
        """,
            countQuery = "SELECT COUNT(*) FROM companies GROUP BY sector",
            nativeQuery = true
    )
    Page<SectorPerfomance> findSectorPerformance(Pageable pageable);

    boolean existsByTicker(String ticker);
}
