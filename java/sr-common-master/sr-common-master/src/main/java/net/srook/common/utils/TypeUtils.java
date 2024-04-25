package net.srook.common.utils;

import static java.util.Arrays.stream;
import static java.util.Objects.isNull;
import static net.srook.common.utils.ApiUtils.PARAM_VALUES_DELIMITER;
import static net.srook.common.utils.ValidateUtils.validateObjectIsNotNull;
import static org.springframework.util.StringUtils.hasText;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.srook.common.exception.SRNumberFormatException;
import net.srook.common.exception.SRTransferException;

public class TypeUtils {
    private TypeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Long toLong(Object object) {
        validateObjectIsNotNull(object, "입력된 Object는 Null입니다.");
        if (isStringType(object)) {
            return toLong(toString(object));
        }
        return toLongFromNumberTypes(object);
    }

    private static Long toLongFromNumberTypes(Object object) {
        validateObjectCanBeConvertedToLongType(object);
        if (isIntegerType(object)) {
            return (long) (int) object;
        }
        return (long) object;
    }

    public static String toString(Object object) {
        return String.valueOf(object);
    }

    public static Long toLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException numberFormatException) {
            throw new SRNumberFormatException("입력된 Value는 Long타입으로 변환이 불가능한 값 입니다.");
        }
    }

    private static void validateObjectCanBeConvertedToLongType(Object object) {
        if (!isIntegerType(object) && !isLongType(object)) {
            throw new SRNumberFormatException("입력된 Object는 Long타입으로 변환이 불가능한 타입 입니다.");
        }
    }

    private static boolean isIntegerType(Object object) {
        return object instanceof Integer;
    }

    private static boolean isLongType(Object object) {
        return object instanceof Long;
    }

    private static boolean isStringType(Object object) {
        return object instanceof String;
    }

    public static <T> T convertJsonStringToObject(final String jsonStr, Class<T> objects) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonStr, objects);
        } catch (JsonProcessingException e) {
            throw new SRTransferException("데이터 변환 중 오류가 발생했습니다. [ jsonStr : {jsonStr}]"
                    .replace("{jsonStr}", jsonStr)
                    , e);
        }
    }

    public static String orNull(final String text) {
        if (hasText(text)) {
            return text;
        }
        return null;
    }

    public static List<Long> toLongList(final String paramValues, final String regex) {
        if (isNull(paramValues)) {
            return Collections.emptyList();
        }
        return stream(paramValues.split(regex))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public static List<Long> toLongList(final String paramValues) {
        if (isNull(paramValues)) {
            return Collections.emptyList();
        }
        return stream(paramValues.split(PARAM_VALUES_DELIMITER))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public static List<String> toStringList(final String paramValues) {
        if (isNull(paramValues)) {
            return Collections.emptyList();
        }
        return stream(paramValues.split(PARAM_VALUES_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static <T> List<T> toList(final T... arr) {
        return Arrays.asList(arr);
    }
}
