package StockQuery.demo.controller;

import StockQuery.demo.service.VolumeAnalysisService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import StockQuery.demo.dto.request.CompanyFilter;
import StockQuery.demo.dto.request.StockPriceHistoryFilter;
import StockQuery.demo.dto.request.VolumeSpikeRequest;
import StockQuery.demo.dto.response.PageResult;
import StockQuery.demo.dto.response.SectorPerfomance;
import StockQuery.demo.dto.response.TopGainerOrLosers;
import StockQuery.demo.dto.response.VolumeSpikeResponse;
import StockQuery.demo.repository.entity.Company;
import StockQuery.demo.repository.entity.StockPriceHistory;
import StockQuery.demo.service.CompanyService;
import StockQuery.demo.service.SectorPerformanceService;
import StockQuery.demo.service.StockPriceHistoryService;
import StockQuery.demo.service.MarketRankingService;
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
