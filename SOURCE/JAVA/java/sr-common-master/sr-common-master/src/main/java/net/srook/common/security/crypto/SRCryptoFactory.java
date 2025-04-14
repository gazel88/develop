package net.srook.common.security.crypto;

public class SRCryptoFactory {
    private static final SRCrypto SROOK_CRYPTO = SrookCrypto.createSrookCrypto();

    private SRCryptoFactory() {
    }

    public static SRCrypto getSrookCrypto() {
        return SROOK_CRYPTO;
    }
}
