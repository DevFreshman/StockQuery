package stockquery.demo.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import stockquery.demo.dto.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<CompanyResponse>> importTicker(@Valid @RequestBody CompanyRequest request) {
        CompanyResponse response = companyService.importTicker(
            request.ticker(), 
            request.companyName(), 
            request.exchange(), 
            request.sector(), 
            request.industry()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("created successfully",response));
    }

    @PostMapping("/import/ticker/{ticker}/history")
    public ResponseEntity<ApiResponse<CompanyHistoryResponse>> postMethodName(@PathParam("ticker") String ticker, @Valid @RequestBody CompanyHistoryRequest request) {
        CompanyHistoryResponse response = stockPriceHistoryService.importTransactionHistory(
            ticker,
            request.tradeDate(),
            request.openPrice(),
            request.highestPrice(),
            request.lowestPrice(),
            request.closePrice(),
            request.volume()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("created successfully",response));
    }
    
    
}
