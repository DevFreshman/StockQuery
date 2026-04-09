package stockquery.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import stockquery.demo.dto.request.CompanyHistoryRequest;
import stockquery.demo.dto.request.CompanyRequest;
import stockquery.demo.dto.response.CompanyHistoryResponse;
import stockquery.demo.dto.response.CompanyResponse;
import stockquery.demo.service.CompanyService;
import stockquery.demo.service.StockPriceHistoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("/vn-stocks")
public class VnStocksCommandController {

    private final CompanyService companyService;
    private final StockPriceHistoryService stockPriceHistoryService;

    @PostMapping("/import/ticker")
    public ResponseEntity<CompanyResponse> importTicker(@RequestBody CompanyRequest request) {
        CompanyResponse response = companyService.importTicker(
            request.ticker(), 
            request.companyName(), 
            request.exchange(), 
            request.sector(), 
            request.industry()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/import/ticker/{ticker}/history")
    public ResponseEntity<CompanyHistoryResponse> postMethodName(@PathParam("ticker") String ticker, @RequestBody CompanyHistoryRequest request) {
        CompanyHistoryResponse response = stockPriceHistoryService.importTransactionHistory(
            ticker,
            request.tradeDate(),
            request.openPrice(),
            request.highestPrice(),
            request.lowestPrice(),
            request.closePrice(),
            request.volume()
        );
        return ResponseEntity.ok(response);
    }
    
    
}
