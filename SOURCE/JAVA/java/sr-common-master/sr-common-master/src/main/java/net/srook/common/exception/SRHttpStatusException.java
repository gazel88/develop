package net.srook.common.exception;

public class SRHttpStatusException extends SRException {
    public SRHttpStatusException(final String message) {
        super(message);
    }

    public SRHttpStatusException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
