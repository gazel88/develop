package net.srook.common.utils;

import static java.lang.String.format;
import static java.net.URLDecoder.decode;
import static org.springframework.util.StringUtils.hasText;
import static org.springframework.web.util.HtmlUtils.htmlEscape;
import static org.springframework.web.util.HtmlUtils.htmlUnescape;

import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import net.srook.common.exception.SRIllegalStateException;

public class StringUtils {

    private StringUtils() {
        throw new SRIllegalStateException("Utility class");
    }

    public static StringJoiner stringJoinerBuild(String delimiter) {
        return new StringJoiner(delimiter);
    }

    public static String encodingToEscape(String unescapeText) {
        if (hasText(unescapeText)) {
            return htmlEscape(unescapeText);
        }
        return null;
    }

    public static String extractTextWithoutHtmlTag(String htmlTagText) {
        if (hasText(htmlTagText)) {
            return decodingToUnescape(Jsoup.clean(htmlTagText, Safelist.none()));
        }
        return null;
    }

    public static String decodingToUnescape(String escapeText) {
        if (hasText(escapeText)) {
            return htmlUnescape(escapeText);
        }
        return null;
    }

    public static String toLowerCaseWithTrim(final String text) {
        if (hasText(text)) {
            return text.trim().toLowerCase();
        }
        return null;
    }

    public static String toDecode(final String value) {
        if (hasText(value)) {
            return decode(value, StandardCharsets.UTF_8);
        }
        return null;
    }

    public static String camelToSnake(final String camelStr) {
        return camelStr.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();
    }

    public static String zeroLpad(final int value, final int length) {
        final String format = "%0" + length + "d";
        return format(format, value);
    }
}
