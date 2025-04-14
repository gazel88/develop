package net.srook.common.utils;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.list;
import static net.srook.common.utils.StringUtils.toLowerCaseWithTrim;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpHeadersUtils {

    private HttpHeadersUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static HttpHeaders getDownloadFileHeaders(String fileName, int fileSize, MediaType mediaType) {
        final HttpHeaders httpHeaders = getCrossAccessControlExposeHeaders("content-disposition", "content-type", "content-length");
        httpHeaders.setContentDisposition(ContentDisposition
                .attachment()
                .filename(URLEncoder.encode(fileName, UTF_8))
                .build());
        httpHeaders.setContentLength(fileSize);
        httpHeaders.setContentType(mediaType);
        return httpHeaders;
    }

    public static HttpHeaders getCrossAccessControlExposeHeaders(String... accessHeaderKeys) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccessControlExposeHeaders(Arrays.asList(accessHeaderKeys));
        return httpHeaders;
    }

    public static HttpHeaders getDefaultHeaderWithoutCors() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccessControlExposeHeaders(Arrays.asList("Authorization", "Location"));
        return httpHeaders;
    }

    public static Map<String, List<String>> getHttpHeaders(final HttpServletRequest request) {
        return list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(StringUtils::toLowerCaseWithTrim, key -> list(request.getHeaders(key))));
    }

    public static List<String> getHttpHeaderValues(final HttpServletRequest request, final String headerKey) {
        return getHttpHeaders(request)
                .getOrDefault(toLowerCaseWithTrim(headerKey), List.of());
    }
}
