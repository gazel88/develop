package net.srook.common.exception;

public class KSErrorException extends RuntimeException {
    public KSErrorException() {
        super();
    }

    public KSErrorException(final String message) {
        super(message);
    }

    public KSErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public KSErrorException(final Throwable cause) {
        super(cause);
    }

    protected KSErrorException(final String message, final Throwable cause, final boolean enableSuppression,
                               final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
