package net.srook.common.utils;

import static com.google.common.hash.Hashing.sha256;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.srook.common.exception.SRIllegalArgumentException;
import net.srook.common.exception.SRIllegalStateException;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class CryptoUtils {
    private static final int ENCODER_STRENGTH = 6;
    private static final String BCRYPT_TYPE_REGEX = "\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}";
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder(ENCODER_STRENGTH);
    private static final Pattern BCRYPT_PATTERN = Pattern.compile(BCRYPT_TYPE_REGEX);

    private CryptoUtils() {
        throw new SRIllegalStateException("Utility class");
    }

    public static String encryptPassword(final String rawPassword) {
        return PASSWORD_ENCODER.encode(rawPassword);
    }

    public static boolean matchPassword(final String rawPassword, final String encryptedPassword) {
        validateRawPasswordIsNotBcryptText(rawPassword);
        validateEncryptedPasswordIsBcryptText(encryptedPassword);
        return PASSWORD_ENCODER.matches(rawPassword, encryptedPassword);
    }

    public static String encodeToBase64(final String rawText) {
        return Encoders.BASE64.encode(rawText.getBytes(StandardCharsets.UTF_8));
    }

    public static String encodeToBase64(final byte[] bytes) {
        return Encoders.BASE64.encode(bytes);
    }

    public static String decodeFromBase64(final String encodedText) {
        return new String(Decoders.BASE64.decode(encodedText), StandardCharsets.UTF_8);
    }

    public static String encodeToHashSHA256(final String rawText) {
        return sha256().hashString(rawText, StandardCharsets.UTF_8)
                .toString();
    }

    public static String makeEncodedTextOfSecretKeyFromBase64() {
        return encodeToBase64(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
    }

    private static void validateRawPasswordIsNotBcryptText(final String rawPassword) {
        if (isBcryptType(rawPassword)) {
            throw new SRIllegalArgumentException("평문 암호에 Bcrypt로 암호화된 문자열이 입력되었습니다.");
        }
    }

    private static void validateEncryptedPasswordIsBcryptText(final String encryptedPassword) {
        if (!isBcryptType(encryptedPassword)) {
            throw new SRIllegalArgumentException("비교 대상 암호에 Bcrypt로 암호화 되지 않은 문자열이 입력되었습니다.");
        }
    }

    private static boolean isBcryptType(final String encryptedText) {
        return BCRYPT_PATTERN
                .matcher(encryptedText)
                .matches();
    }
}
