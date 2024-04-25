package net.srook.common.security.crypto;

public interface SRCrypto {
    String encryptPassword(final String rawPassword);

    boolean matchPassword(final String rawPassword, final String encryptedPassword);

    boolean isEncrypted(final String text);
}
