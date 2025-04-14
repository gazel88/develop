package net.srook.common.security.crypto;

import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.srook.common.exception.SRIllegalArgumentException;

class SrookCrypto implements SRCrypto {
    private static final int ENCODER_STRENGTH = 6;
    private static final String BCRYPT_TYPE_REGEX = "\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}";
    private final Pattern bcryptPattern;
    private final PasswordEncoder passwordEncoder;

    public SrookCrypto(final Pattern bcryptPattern, final PasswordEncoder passwordEncoder) {
        this.bcryptPattern = bcryptPattern;
        this.passwordEncoder = passwordEncoder;
    }

    static SrookCrypto createSrookCrypto() {
        return createSrookCrypto(ENCODER_STRENGTH);
    }

    static SrookCrypto createSrookCrypto(final int encoderStrength) {
        return new SrookCrypto(Pattern.compile(BCRYPT_TYPE_REGEX), new BCryptPasswordEncoder(encoderStrength));
    }

    @Override
    public String encryptPassword(final String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matchPassword(final String rawPassword, final String encryptedPassword) {
        validateRawPasswordIsNotBcryptText(rawPassword);
        validateEncryptedPasswordIsBcryptText(encryptedPassword);
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }

    @Override
    public boolean isEncrypted(final String text) {
        return isBcryptType(text);
    }

    private void validateRawPasswordIsNotBcryptText(final String rawPassword) {
        if (isBcryptType(rawPassword)) {
            throw new SRIllegalArgumentException("평문 암호에 Bcrypt로 암호화된 문자열이 입력되었습니다.");
        }
    }

    private void validateEncryptedPasswordIsBcryptText(final String encryptedPassword) {
        if (!isBcryptType(encryptedPassword)) {
            throw new SRIllegalArgumentException("비교 대상 암호에 Bcrypt로 암호화 되지 않은 문자열이 입력되었습니다.");
        }
    }

    private boolean isBcryptType(final String encryptedText) {
        return bcryptPattern
                .matcher(encryptedText)
                .matches();
    }
}
