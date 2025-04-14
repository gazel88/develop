package net.srook.common.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class SRNotFoundException extends SRException {
    public SRNotFoundException(final String message) {
        super(message, NOT_FOUND);
    }
}
