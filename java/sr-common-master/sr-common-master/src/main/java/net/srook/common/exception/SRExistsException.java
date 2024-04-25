package net.srook.common.exception;

public class SRExistsException extends SRException {
    public SRExistsException(final String message) {
        super(message);
    }

    public SRExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
