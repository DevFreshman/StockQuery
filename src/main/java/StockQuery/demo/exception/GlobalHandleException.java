package stockquery.demo.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import stockquery.demo.dto.response.ApiResponse;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalHandleException{

    //Custom Exceptions
    @ExceptionHandler(HistoryIsExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleHistoryIsExistsException(HistoryIsExistsException ex){
        List<String> errors = new ArrayList<>();
        errors.add(ex.ticker());
        errors.add(ex.tradeDate().toString());
            return ResponseEntity
                    .status(409)
                    .body(ApiResponse.error("History Already Exists","HISTORY_ALREADY_EXISTS",errors));
    }

    @ExceptionHandler(TickerIsExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleTickerIsExistsException(TickerIsExistsException ex){
        return ResponseEntity
                .status(409)
                .body(ApiResponse.error(ex.getMessage(), "TICKER_ALREADY_EXISTS",ex.ticker()));
    }


    // DataBase Exceptions
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrityViolationException(){
            return ResponseEntity
                    .status(409)
                    .body(ApiResponse.error("Data integrity violation", "DATA_INTEGRITY_VIOLATION",null));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataAccessException(){
            return ResponseEntity
                    .status(503)
                    .body(ApiResponse.error("Database error occurred", "DATABASE_ERROR",null));
    }

    // Validation Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex){

        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + " : " + err.getDefaultMessage())
                .toList();

        return ResponseEntity
                .status(400)
                .body(ApiResponse.error("Validation failed","VALIDATION_ERROR",errors));
    }

    // Handle unexpected exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleUnexpectedException(){
        return ResponseEntity
                .status(500)
                .body(ApiResponse.error("An unexpected error occurred", "INTERNAL_SERVER_ERROR",null));
    }
}
