package net.srook.common.utils;

import static java.lang.String.valueOf;
import static java.net.URLDecoder.decode;
import static java.net.URLEncoder.encode;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static net.srook.common.utils.StringUtils.camelToSnake;
import static net.srook.common.utils.TypeUtils.toList;
import static net.srook.common.utils.ValidateUtils.isNullOrEmpty;
import static org.springframework.util.StringUtils.hasText;
import static org.springframework.web.util.UriComponentsBuilder.newInstance;

import java.lang.reflect.Field;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.util.UriComponentsBuilder;

import net.srook.common.dto.ErrorResponse;
import net.srook.common.exception.SRApiServerException;
import net.srook.common.exception.SRIllegalArgumentException;
import net.srook.common.exception.SRIllegalStateException;

import reactor.core.publisher.Mono;

public class ApiUtils {

    public static final String PARAM_VALUES_DELIMITER = ",";
    public static final String DELIMITER_COMMA = ",";
    public static final String SCHEME_HTTP = "http";
    public static final String SCHEME_HTTPS = "https";

    private ApiUtils() {
        throw new SRIllegalStateException("Utility class");
    }

    public static <T> ResponseEntity<T> create(final String uri) {
        return ResponseEntity.created(URI.create(uri)).build();
    }

    public static ResponseEntity<HttpStatus> ok() {
        return ResponseEntity.ok().build();
    }

    public static <T> ResponseEntity<T> ok(final T body) {
        return ResponseEntity.ok(body);
    }

    public static <T> ResponseEntity<T> ok(final HttpHeaders httpHeaders, final T body) {
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(body);
    }

    public static <T> String toArrayParams(final List<T> params) {
        StringJoiner joiner = new StringJoiner(DELIMITER_COMMA);
        params.forEach(param -> joiner.add(valueOf(param)));
        return joiner.toString();
    }

    public static List<String> converterStringParameters(final String param) {
        if (hasText(param)) {
            return toList(decode(param, StandardCharsets.UTF_8).split(DELIMITER_COMMA));
        }
        return emptyList();
    }

    public static List<Long> converterLongParameters(final String param) {
        if (hasText(param)) {
            return toList(param.split(DELIMITER_COMMA))
                    .stream()
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }
        return emptyList();
    }

    public static ResponseEntity<HttpStatus> ok(final HttpHeaders httpHeaders) {
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .build();
    }

    public static UriComponentsBuilder fromUriString(final String uri) {
        return UriComponentsBuilder.fromUriString(uri);
    }

    public static UriComponentsBuilder createHttpUri() {
        return newInstance().scheme(SCHEME_HTTP);
    }

    public static UriComponentsBuilder createHttpsUri() {
        return newInstance().scheme(SCHEME_HTTPS);
    }

    public String makeParamValues(final String... values) {
        StringJoiner joiner = new StringJoiner(PARAM_VALUES_DELIMITER);
        for (String value : values) {
            joiner.add(value);
        }
        return joiner.toString();
    }

    public String makeParamValues(final Long... values) {
        StringJoiner joiner = new StringJoiner(PARAM_VALUES_DELIMITER);
        for (Long value : values) {
            joiner.add(valueOf(value));
        }
        return joiner.toString();
    }

    public static Object getEmptyValue(Object value) {
        if (isNull(value)) {
            return "";
        }
        return value;
    }

    public static String makeParam(final String key, final Object value) {
        if (hasText(key) && !isNull(value)) {
            return "&" + key + "=" + value;
        }
        return "";
    }

    public static String toDecode(final String value) {
        if (hasText(value)) {
            return decode(value, StandardCharsets.UTF_8);
        }
        return null;
    }

    public static String toEncode(final String value) {
        if (hasText(value)) {
            return encode(value, StandardCharsets.UTF_8);
        }
        return null;
    }

    public static <T> String makeOrderBy(final List<String> orderKeys, final Class<T> classObject) {
        final List<String> fieldNames = getFieldNames(classObject);
        StringJoiner joiner = new StringJoiner(", ");
        for (String orderKey : orderKeys) {
            joiner.add(getOrderBy(orderKey, fieldNames));
        }
        return joiner.toString();
    }

    public static <T> String makeOrderBy(final String orderKeys, final Class<T> classObject) {
        final List<String> fieldNames = getFieldNames(classObject);
        StringJoiner joiner = new StringJoiner(", ");
        for (String orderKey : converterStringParameters(orderKeys)) {
            joiner.add(getOrderBy(orderKey, fieldNames));
        }
        return joiner.toString();
    }

    public static Mono<Throwable> getThrowableMono(final ClientResponse clientResponse) {
        return clientResponse.bodyToMono(ErrorResponse.class)
                .flatMap(ApiUtils::getError);
    }

    public static String getRequestApiUri(final HttpServletRequest request) {
        return request.getRequestURI() + getRequestApiQueryString(request);
    }

    private static String getRequestApiQueryString(final HttpServletRequest request) {
        final String queryString = request.getQueryString();
        if (isNullOrEmpty(queryString)) {
            return "";
        }
        return "?" + queryString;
    }

    public static String getClientIP(final HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (!isNull(ipAddress) && ipAddress.split(",").length > 0) {
            return ipAddress.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    private static Mono<Throwable> getError(final ErrorResponse errorResponse) {
        return Mono.error(() ->
                new SRApiServerException("API Server Error - " + errorResponse.getErrorMessage())
        );
    }

    private static <T> List<String> getFieldNames(final Class<T> classObject) {
        return toList(classObject.getDeclaredFields())
                .stream()
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    private static String getOrderBy(final String orderKey, final List<String> fieldNames) {
        if (orderKey.indexOf("-") == 0) {
            validateExistColumn(orderKey.substring(1), fieldNames);
            return camelToSnake(orderKey.substring(1)).toUpperCase() + " DESC";
        }
        validateExistColumn(orderKey, fieldNames);
        return camelToSnake(orderKey).toUpperCase();
    }

    private static void validateExistColumn(final String orderKey, final List<String> fieldNames) {
        if (fieldNames.stream().noneMatch(fieldName -> fieldName.equals(orderKey))) {
            throw new SRIllegalArgumentException("Order By를 위한 유효한 필드가 존재하지 않습니다. Input : {value}"
                    .replace("{value}", orderKey));
        }
    }
}
