package net.srook.common.exception;

public class SRDuplicateException extends SRException {
    public SRDuplicateException(final String message) {
        super(message);
    }

    public SRDuplicateException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
