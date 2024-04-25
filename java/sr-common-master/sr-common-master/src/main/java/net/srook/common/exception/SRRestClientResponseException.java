package net.srook.common.exception;

public class SRRestClientResponseException extends SRException {
    public SRRestClientResponseException(final String message) {
        super(message);
    }

    public SRRestClientResponseException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
