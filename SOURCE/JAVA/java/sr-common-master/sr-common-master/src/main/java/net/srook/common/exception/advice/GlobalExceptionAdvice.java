package net.srook.common.exception.advice;

import static net.srook.common.utils.ApiUtils.getClientIP;
import static net.srook.common.utils.ApiUtils.getRequestApiUri;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import net.srook.common.dto.ErrorResponse;
import net.srook.common.exception.KSErrorException;
import net.srook.common.exception.SRException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(SRException.class)
    public ResponseEntity<ErrorResponse> handlingRuntimeException(SRException exception, final HttpServletRequest request) {
        log.warn("[GlobalExceptionAdvice::KSException] client-ip: '{}'. uri: '{} {}'. message: {}",
                getClientIP(request), request.getMethod(), getRequestApiUri(request), exception.getMessage(), exception);
        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(exception.getStatusCd(), exception.getMessage()));
    }

    @ExceptionHandler({Exception.class, KSErrorException.class})
    public ResponseEntity<ErrorResponse> handlingException(Exception exception, final HttpServletRequest request) {
        log.error("[GlobalExceptionAdvice::Exception] client-ip: '{}'. uri: '{} {}'. message: {}",
                getClientIP(request), request.getMethod(), getRequestApiUri(request), exception.getMessage(), exception);
        return ResponseEntity.internalServerError()
                .body(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
