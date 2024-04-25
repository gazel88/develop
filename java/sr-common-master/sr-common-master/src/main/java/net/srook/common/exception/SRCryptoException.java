package net.srook.common.exception;

public class SRCryptoException extends SRException {
    public SRCryptoException(final String message) {
        super(message);
    }

    public SRCryptoException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
