package net.srook.common.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class SRAuthorizationException extends SRException {
    public SRAuthorizationException(final String message) {
        super(message, UNAUTHORIZED);
    }

    public SRAuthorizationException(final String message, final Throwable cause) {
        super(message, cause, UNAUTHORIZED);
    }
}
