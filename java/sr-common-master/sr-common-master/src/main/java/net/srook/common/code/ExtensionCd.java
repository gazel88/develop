package net.srook.common.code;

import static org.springframework.http.MediaType.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;

import net.srook.common.exception.SRNotFoundException;

import lombok.Getter;

@Getter
public enum ExtensionCd {
    EXT_PNG("png", FileTycd.IMG, IMAGE_PNG),
    EXT_JPG("jpg", FileTycd.IMG, IMAGE_JPEG),
    EXT_JPEG("jpeg", FileTycd.IMG, IMAGE_JPEG),
    EXT_BMP("bmp", FileTycd.IMG, MediaType.valueOf("image/bmp")),
    EXT_GIF("gif", FileTycd.IMG, IMAGE_GIF),
    EXT_TIF("tif", FileTycd.IMG, MediaType.valueOf("image/tiff")),
    EXT_TIFF("tiff", FileTycd.IMG, MediaType.valueOf("image/tiff")),
    EXT_TXT("txt", FileTycd.DOC, TEXT_PLAIN),
    EXT_MD("md", FileTycd.DOC, TEXT_MARKDOWN),
    EXT_PPT("ppt", FileTycd.DOC, MediaType.valueOf("application/vnd.ms-powerpoint")),
    EXT_PPTX("pptx", FileTycd.DOC, MediaType.valueOf("application/vnd.ms-powerpoint")),
    EXT_DOC("doc", FileTycd.DOC, MediaType.valueOf("application/msword")),
    EXT_DOCX("docx", FileTycd.DOC, MediaType.valueOf("application/msword")),
    EXT_XLS("xls", FileTycd.DOC, MediaType.valueOf("application/vnd.ms-excel")),
    EXT_XLSX("xlsx", FileTycd.DOC, MediaType.valueOf("application/vnd.ms-excel")),
    EXT_CSV("csv", FileTycd.DOC, MediaType.valueOf("text/csv")),
    EXT_PDF("pdf", FileTycd.DOC, APPLICATION_PDF),
    EXT_AI("ai", FileTycd.DOC, MediaType.valueOf("application/postscript")),
    EXT_PSD("psd", FileTycd.DOC, MediaType.valueOf("image/vnd.adobe.photoshop")),
    EXT_HWP("hwp", FileTycd.DOC, MediaType.valueOf("application/vnd.hancom.hwp")),
    EXT_ZIP("zip", FileTycd.ZIP, MediaType.valueOf("application/zip")),
    EXT_RAR("rar", FileTycd.ZIP, MediaType.valueOf("application/x-rar-compressed")),
    EXT_7Z("7z", FileTycd.ZIP, MediaType.valueOf("application/x-7z-compressed")),
    EXT_TAR("tar", FileTycd.ZIP, MediaType.valueOf("application/x-tar"));

    private static final String REPLACE_KEY_EXTENSION = "{extension}";
    private static final String ERROR_MESSAGE_NOT_ALLOWED_EXTENSION = "허용되지 않은 확장자입니다. * 확장자 : " + REPLACE_KEY_EXTENSION;
    private final String extension;
    private final FileTycd fileTycd;
    private final MediaType mediaType;

    ExtensionCd(final String extension, final FileTycd fileTycd, final MediaType mediaType) {
        this.extension = extension;
        this.fileTycd = fileTycd;
        this.mediaType = mediaType;
    }

    public static List<String> getExtensionsBy(final FileTycd fileTycd) {
        return Arrays.stream(values())
                .filter(cd -> cd.fileTycd.equals(fileTycd))
                .map(cd -> cd.extension)
                .collect(Collectors.toList());
    }

    public static FileTycd getFileTycdBy(final String extension) {
        return findExtensionCdBy(extension)
                .map(cd -> cd.fileTycd)
                .orElseThrow(toKsNotFoundExceptionThrow(extension));
    }

    public static MediaType getMediaType(final String extension) {
        return findExtensionCdBy(extension)
                .map(cd -> cd.mediaType)
                .orElseThrow(toKsNotFoundExceptionThrow(extension));
    }

    public static ExtensionCd findCdBy(final String extension) {
        return findExtensionCdBy(extension)
                .orElseThrow(toKsNotFoundExceptionThrow(extension));
    }

    public String getFileType() {
        return this.fileTycd.getName();
    }

    private static Supplier<SRNotFoundException> toKsNotFoundExceptionThrow(final String extension) {
        return () -> new SRNotFoundException(ERROR_MESSAGE_NOT_ALLOWED_EXTENSION.replace(REPLACE_KEY_EXTENSION, extension));
    }

    private static Optional<ExtensionCd> findExtensionCdBy(final String extension) {
        return Arrays.stream(values())
                .filter(cd -> cd.equalsExtension(extension))
                .findFirst();
    }

    private boolean equalsExtension(final String extension) {
        return this.extension.equalsIgnoreCase(extension);
    }
}
