package net.srook.common.utils;

import static net.srook.common.utils.ValidateUtils.validateValueIsNotNullOrEmpty;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.core.io.DefaultResourceLoader;

import net.srook.common.exception.KSIOException;
import net.srook.common.exception.SRIllegalStateException;
import net.srook.common.exception.SRTransferException;

public class FileUtils {
    private static final String DELIMITER_POINT = ".";
    private static final int FIXED_INDEX_LENGTH = 1;
    public static final String ERROR_FILENAME_IS_NULL = "파일 이름이 존재하지 않습니다.";

    private FileUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static InputStream toInputStream(final String encodedFileText) {
        try {
            return new ByteArrayInputStream(toBytes(encodedFileText));
        } catch (Exception e) {
            throw new KSIOException("Byte배열을 InputStream으로 변환하는 도중 오류가 발생했습니다.", e);
        }
    }

    public static byte[] toBytes(final String encodedFileText) {
        try {
            return Base64.getDecoder()
                    .decode(encodedFileText);
        } catch (Exception e) {
            throw new SRTransferException("Base64로 인코딩 된 문자열을 Byte 배열로 변환하는 도중 오류가 발생했습니다.", e);
        }
    }

    public static byte[] readResource(final String resourcePath) {
        try {
            return new DefaultResourceLoader()
                    .getResource(makeResourceLocation(resourcePath))
                    .getInputStream()
                    .readAllBytes();
        } catch (IOException e) {
            throw new SRIllegalStateException("파일을 읽는 중 오류가 발생했습니다.", e);
        }
    }

    private static String makeResourceLocation(final String resourcePath) {
        if (resourcePath.startsWith("classpath:")) {
            return resourcePath;
        }
        return "classpath:" + resourcePath;
    }

    public static String toEncodedText(final byte[] bytes) {
        try {
            return Base64.getEncoder()
                    .encodeToString(bytes);
        } catch (Exception e) {
            throw new SRTransferException("Byte 배열을 Base64로 인코딩 된 문자열로 변환하는 도중 오류가 발생했습니다.", e);
        }
    }

    public static int getFileSize(final String encodedFileText) {
        try {
            return toBytes(encodedFileText).length;
        } catch (Exception e) {
            throw new SRTransferException("Byte 배열의 길이를 읽는 도중 오류가 발생했습니다.", e);
        }
    }

    public static String extractExtension(final String fileFullName) {
        validateOriginalFileNameIsNotNullAndEmpty(fileFullName);
        return fileFullName.substring(getExtensionStartPosition(fileFullName));
    }

    private static void validateOriginalFileNameIsNotNullAndEmpty(final String fileFullName) {
        validateValueIsNotNullOrEmpty(fileFullName, ERROR_FILENAME_IS_NULL);
    }

    private static int getExtensionStartPosition(final String originalFilename) {
        return originalFilename.lastIndexOf(DELIMITER_POINT) + FIXED_INDEX_LENGTH;
    }
}
