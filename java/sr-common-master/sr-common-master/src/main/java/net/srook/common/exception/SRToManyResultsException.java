package net.srook.common.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class SRToManyResultsException extends SRException {
    public SRToManyResultsException(final String message) {
        super(message, NOT_FOUND);
    }
}
