package net.srook.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class SRException extends RuntimeException {
    private HttpStatus statusCd = HttpStatus.BAD_REQUEST;
    public SRException() {
        super();
    }

    public SRException(final String message) {
        super(message);
    }

    public SRException(final String message, final HttpStatus statusCd) {
        super(message);
        this.statusCd = statusCd;
    }

    public SRException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SRException(final String message, final Throwable cause, final HttpStatus statusCd) {
        super(message, cause);
        this.statusCd = statusCd;
    }

    public SRException(final Throwable cause) {
        super(cause);
    }

    public SRException(final Throwable cause, final HttpStatus statusCd) {
        super(cause);
        this.statusCd = statusCd;
    }

    protected SRException(final String message, final Throwable cause, final boolean enableSuppression,
                          final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected SRException(final String message, final Throwable cause, final boolean enableSuppression,
                          final boolean writableStackTrace, final HttpStatus statusCd) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.statusCd = statusCd;
    }
}
