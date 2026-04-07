package StockQuery.demo.repository.jpa.query;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import StockQuery.demo.dto.response.TopGainerOrLosers;
import StockQuery.demo.dto.response.VolumeSpikeResponse;
import StockQuery.demo.repository.entity.StockPriceHistory;

@Repository
public interface JpaStockPriceHistoryQuery extends JpaRepository<StockPriceHistory, Long>, JpaSpecificationExecutor<StockPriceHistory> {
    
    @Query(
    value = """

    WITH ranked AS (
    SELECT 
        ticker,
        trade_date,
        close_price,
        LAG(close_price, 1) OVER (PARTITION BY ticker ORDER BY trade_date) AS prev_close,
        LAG(trade_date, 1)  OVER (PARTITION BY ticker ORDER BY trade_date) AS prev_date,
        ROW_NUMBER()        OVER (PARTITION BY ticker ORDER BY trade_date DESC) AS rn
    FROM hs_stock_prices
    )
    SELECT 
        ticker,
        trade_date AS latest_date,
        close_price AS latest_close,
        prev_date,
        prev_close,
        ROUND(close_price - prev_close, 2) AS price_change,
        ROUND((close_price - prev_close) / NULLIF(prev_close, 0) * 100, 2) AS pct_change
    FROM ranked
    WHERE rn = 1 and prev_close IS NOT NULL
    ORDER BY pct_change DESC

        """,
    countQuery = """
        SELECT COUNT(*) 
        FROM (
            SELECT ticker
            FROM hs_stock_prices
            GROUP BY ticker
            ) t
        """,
    nativeQuery = true
    )
    Page<TopGainerOrLosers> findTopGainers(
        Pageable pageable
    );

    @Query(
    value = """

    WITH ranked AS (
    SELECT 
        ticker,
        trade_date,
        close_price,
        LAG(close_price, 1) OVER (PARTITION BY ticker ORDER BY trade_date) AS prev_close,
        LAG(trade_date, 1)  OVER (PARTITION BY ticker ORDER BY trade_date) AS prev_date,
        ROW_NUMBER()        OVER (PARTITION BY ticker ORDER BY trade_date DESC) AS rn
    FROM hs_stock_prices
    )
    SELECT 
        ticker,
        trade_date AS latest_date,
        close_price AS latest_close,
        prev_date,
        prev_close,
        ROUND(close_price - prev_close, 2) AS price_change,
        ROUND((close_price - prev_close) / NULLIF(prev_close, 0) * 100, 2) AS pct_change
    FROM ranked
    WHERE rn = 1 and prev_close IS NOT NULL
    ORDER BY pct_change ASC

        """,
    countQuery = """
        SELECT COUNT(*) 
        FROM (
            SELECT ticker
            FROM hs_stock_prices
            GROUP BY ticker
            ) t
        """,
    nativeQuery = true
    )
    Page<TopGainerOrLosers> findTopLosers(
        Pageable pageable
    );


    @Query(
    value = """
    WITH baseline AS (
    SELECT
        ticker,
        trade_date,
        AVG(volume) OVER (
            PARTITION BY ticker
            ORDER BY trade_date
            ROWS BETWEEN  :baselineDays PRECEDING AND 1 PRECEDING
        ) AS avg_volume
    from hs_stock_prices
    )
    SELECT
        sp.ticker,
        sp.trade_date ,
        sp.volume,
        b.avg_volume,
        ROUND(sp.volume / NULLIF(b.avg_volume, 0), 2) AS spike_ratio,
        case when sp.volume > b.avg_volume * :spikeThreshold then true else false end AS is_spike
    FROM hs_stock_prices sp
    JOIN baseline b
        ON sp.ticker = b.ticker
        AND sp.trade_date = b.trade_date
    WHERE sp.trade_date = :date
    order by spike_ratio desc;
        """,
    countQuery = """
        SELECT COUNT(*) 
        FROM (
            SELECT ticker
            FROM hs_stock_prices
            GROUP BY ticker
            ) t
        """,
    nativeQuery = true
    )
    Page<VolumeSpikeResponse> findVolumeSpikesByDate(
        Pageable pageable, 
        @Param("date") LocalDate date, 
        @Param("baselineDays") int baselineDays, 
        @Param("spikeThreshold") double spikeThreshold);

}
