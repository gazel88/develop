package net.srook.common.exception;

public class SRTransferException extends SRException {
    public SRTransferException(final String message) {
        super(message);
    }

    public SRTransferException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
