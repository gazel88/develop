package net.srook.common.exception;

public class SRIllegalStateException extends SRException {
    public SRIllegalStateException(final String message) {
        super(message);
    }

    public SRIllegalStateException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
