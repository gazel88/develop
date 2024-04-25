package net.srook.common.exception;

public class SRRestClientException extends SRException {
    public SRRestClientException(final String message) {
        super(message);
    }

    public SRRestClientException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
