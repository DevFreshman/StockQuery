package stockquery.demo.controller;

import stockquery.demo.service.VolumeAnalysisService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stockquery.demo.dto.request.CompanyFilter;
import stockquery.demo.dto.request.StockPriceHistoryFilter;
import stockquery.demo.dto.request.VolumeSpikeRequest;
import stockquery.demo.dto.response.PageResult;
import stockquery.demo.dto.response.SectorPerfomance;
import stockquery.demo.dto.response.TopGainerOrLosers;
import stockquery.demo.dto.response.VolumeSpikeResponse;
import stockquery.demo.repository.entity.Company;
import stockquery.demo.repository.entity.StockPriceHistory;
import stockquery.demo.service.CompanyService;
import stockquery.demo.service.SectorPerformanceService;
import stockquery.demo.service.StockPriceHistoryService;
import stockquery.demo.service.MarketRankingService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@RequiredArgsConstructor
@RestController
@RequestMapping("/vn-stocks")
public class VnStocksQueryController {

    private final StockPriceHistoryService stockPriceHistoryService;
    private final CompanyService companyService;
    private final SectorPerformanceService sectorPerformanceService;
    private final MarketRankingService marketRankingService;
    private final VolumeAnalysisService volumeAnalysisService;

    @GetMapping("/stock-price")
    public ResponseEntity<PageResult<StockPriceHistory>> getStockPriceList(
        @ModelAttribute StockPriceHistoryFilter filter, 
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        
        PageResult<StockPriceHistory> result = stockPriceHistoryService.findHistory(PageRequest.of(page, size), filter);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/companies")
    public ResponseEntity<PageResult<Company>> getCompaniesList(
        @ModelAttribute CompanyFilter filter,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

        PageResult<Company> result = companyService.findCompanies(PageRequest.of(page, size), filter);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/performance/sector")
    public ResponseEntity<PageResult<SectorPerfomance>> getIndustryPerformance(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

        PageResult<SectorPerfomance> result = sectorPerformanceService.findSectorPerformance(PageRequest.of(page, size));
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/top/gainer")
    public ResponseEntity<PageResult<TopGainerOrLosers>> getTopGainers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        PageResult<TopGainerOrLosers> result = marketRankingService.findTopGainers(PageRequest.of(page, size));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/top/loser")
    public ResponseEntity<PageResult<TopGainerOrLosers>> getTopLosers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        PageResult<TopGainerOrLosers> result = marketRankingService.findTopLosers(PageRequest.of(page, size));
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/volume/spikes")
    public ResponseEntity<PageResult<VolumeSpikeResponse>> getVolumeSpikes(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @ModelAttribute VolumeSpikeRequest request
    ) {
        PageResult<VolumeSpikeResponse> result = volumeAnalysisService.findVolumeSpikes(PageRequest.of(page, size), request);
        return ResponseEntity.ok(result);
    }
    
    
}
