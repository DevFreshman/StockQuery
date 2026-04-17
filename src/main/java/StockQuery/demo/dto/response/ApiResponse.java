package stockquery.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.Instant;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private String errorCode;
    private T data;
    private Instant timestamp;

    public static <T> ApiResponse<T> success(String message,T data) {
        return ApiResponse.<T>builder()
                .message(message)
                .success(true)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> error(String message,String errorCode, T data) {
        return ApiResponse.<T>builder()
                .message(message)
                .success(false)
                .errorCode(errorCode)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }
}
