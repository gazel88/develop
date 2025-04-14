package net.srook.common.exception;

public class SREmailException extends SRException {
    public SREmailException(final String message) {
        super(message);
    }

    public SREmailException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
