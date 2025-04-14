package net.srook.common.exception;

public class KSIOException extends SRException {
    public KSIOException(final String message) {
        super(message);
    }

    public KSIOException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
