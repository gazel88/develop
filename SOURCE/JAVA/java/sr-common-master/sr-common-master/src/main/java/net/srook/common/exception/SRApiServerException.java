package net.srook.common.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class SRApiServerException extends SRException {
    public SRApiServerException(final String message) {
        super(message, UNAUTHORIZED);
    }

    public SRApiServerException(final String message, final Throwable cause) {
        super(message, cause, UNAUTHORIZED);
    }
}
