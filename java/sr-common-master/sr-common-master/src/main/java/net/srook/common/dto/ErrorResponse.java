package net.srook.common.dto;

import static net.srook.common.utils.DateUtils.getNowDateToString;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponse {
    private static final String DEFAULT_ERROR_MESSAGE = "예기치 못한 오류가 발생하였습니다. 서버 로그 확인 바랍니다.";
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    @Schema(description = "발생시간")
    private String timestamp;
    @Schema(description = "상태코드")
    private int statusCd;
    @Schema(description = "상태")
    private String status;
    @Schema(description = "에러 메시지")
    private String errorMessage;

    protected ErrorResponse() {
    }

    private ErrorResponse(final String timestamp, final int statusCd, final String status, final String errorMessage) {
        this.statusCd = statusCd;
        this.status = status;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public static ErrorResponse of(final HttpStatus status, final String errorMessage) {
        return new ErrorResponse(getNowDateToString(DATE_TIME_PATTERN), status.value(), status.name(), errorMessage);
    }

    public static ErrorResponse of(final HttpStatus status) {
        return new ErrorResponse(getNowDateToString(DATE_TIME_PATTERN), status.value(), status.name(), DEFAULT_ERROR_MESSAGE);
    }
}
