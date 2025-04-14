package net.srook.common.utils;

import static java.util.Objects.isNull;
import static java.util.regex.Pattern.matches;
import static org.springframework.util.StringUtils.hasText;

import java.util.List;

import net.srook.common.exception.SRIllegalArgumentException;
import net.srook.common.exception.SRIllegalStateException;

public class ValidateUtils {
    private static final String REGEX_TELL_NUMBER_WITHOUT_HYPHEN = "\\d{9,11}";
    private static final String REGEX_EMAIL = "^[0-9a-zA-Z._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$";
    private static final String REGEX_DATE_YYYY = "^[12]\\d{3}$";
    private static final String REGEX_DATE_MM = "^(0[1-9]|1[012])$";
    private static final String REGEX_DATE_YYYYMM = "^[12]\\d{3}(0[1-9]|1[012])$";
    private static final String REGEX_DATE_YYMM = "^\\d{2}(0[1-9]|1[012])$";
    private static final String REGEX_DATE_YYYYMMDD = "^[12]\\d{3}(0[1-9]|1[012])(0[1-9]|[12]\\d|3[0-1])$";
    private static final String REGEX_TIME_HH24MMSS = "^([0-1]\\d|2[0-3])([0-5]\\d)([0-5]\\d)$";
    private static final String REGEX_MEMBER_NO = "^\\d{10}$";
    private static final String REGEX_NUMBER = "^\\d+$";
    private static final String ERROR_MESSAGE_VALUE_PRE_DEFAULT = " Value : <";
    private static final String ERROR_MESSAGE_VALUE_POS_DEFAULT = ">";
    private static final String ERROR_MESSAGE_KEY_PRE = "[";
    private static final String ERROR_MESSAGE_KEY_POST = "] ";
    private static final String ERROR_MESSAGE_REQUIRED_FOR_ID = "ID는 Null 또는 0보다 작은 수일 수 없습니다.";
    public static final String ERROR_MESSAGE_REQUIRED_POST = "(은)는 필수 입력 항목입니다.";
    public static final String ERROR_MESSAGE_TELL_NUMBER_WITHOUT_HYPHEN_DEFAULT = "하이픈(-)을 제외한 전화번호 형식의 데이터가 아닙니다.";
    public static final String ERROR_MESSAGE_EMAIL_DEFAULT = "유효한 이메일 형식의 데이터가 아닙니다.";
    public static final String ERROR_MESSAGE_DATE_TYPE_YEAR_MONTH_DAY_DEFAULT = "YYYYMMDD 날짜 형식의 데이터가 아닙니다.";
    public static final String ERROR_MESSAGE_DATE_TYPE_YEAR_MONTH_DEFAULT = "YYYYMM 날짜 형식의 데이터가 아닙니다.";

    private ValidateUtils() {
        throw new SRIllegalStateException("Utility class");
    }

    public static void validateRequired(final String value, final String itemName) {
        validateValueIsNotNullOrEmpty(value, itemName + ERROR_MESSAGE_REQUIRED_POST);
    }

    public static void validateRequired(final Object value, final String itemName) {
        validateObjectIsNotNull(value, itemName + ERROR_MESSAGE_REQUIRED_POST);
    }

    public static void validateValueTypeOfTellNumberWithoutHyphen(final String tellNumber) {
        validateValueTypeOfTellNumberWithoutHyphen(tellNumber,
                makeDefaultErrorMessage(ERROR_MESSAGE_TELL_NUMBER_WITHOUT_HYPHEN_DEFAULT, tellNumber));
    }

    public static void validateValueTypeOfTellNumberWithoutHyphen(final String tellNumber, final String errorMessage) {
        if (!isTellNumberWithoutHyphen(tellNumber)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static boolean isTellNumberWithoutHyphen(final String tellNumber) {
        if (hasText(tellNumber)) {
            return matches(REGEX_TELL_NUMBER_WITHOUT_HYPHEN, tellNumber);
        }
        return false;
    }

    public static void validateValueTypeOfEmail(final String email) {
        validateValueTypeOfEmail(email, makeDefaultErrorMessage(ERROR_MESSAGE_EMAIL_DEFAULT, email));
    }

    public static void validateValueTypeOfEmail(final String email, final String errorMessage) {
        if (!isEmail(email)) {
            throw new SRIllegalArgumentException(errorMessage);
        }
    }

    public static void validateValueTypeOfYearMonthDay(final String yyyymmdd) {
        if (!isDateYYYYMMDD(yyyymmdd)) {
            throw new SRIllegalArgumentException(makeDefaultErrorMessage(ERROR_MESSAGE_DATE_TYPE_YEAR_MONTH_DAY_DEFAULT, yyyymmdd));
        }
    }

    public static void validateValueTypeOfYearMonthDay(final String yyyymmdd, final String key) {
        if (!isDateYYYYMMDD(yyyymmdd)) {
            throw new SRIllegalArgumentException(makeDefaultErrorMessage(key, ERROR_MESSAGE_DATE_TYPE_YEAR_MONTH_DAY_DEFAULT, yyyymmdd));
        }
    }

    public static void validateId(final Long id) {
        if (isNull(id) || number(id).isLessThanOrEqualZero()) {
            throw new SRIllegalArgumentException(makeDefaultErrorMessage(ERROR_MESSAGE_REQUIRED_FOR_ID, id));
        }
    }

    private static KSLong number(final Long number) {
        return new KSLong(number);
    }

    public static void validateValueTypeOfYearMonth(final String yyyymm) {
        if (!isDateYYYYMM(yyyymm)) {
            throw new SRIllegalArgumentException(makeDefaultErrorMessage(ERROR_MESSAGE_DATE_TYPE_YEAR_MONTH_DEFAULT, yyyymm));
        }
    }

    public static void validateValueTypeOfYearMonth(final String yyyymm, final String key) {
        if (!isDateYYYYMM(yyyymm)) {
            throw new SRIllegalArgumentException(makeDefaultErrorMessage(key, ERROR_MESSAGE_DATE_TYPE_YEAR_MONTH_DEFAULT, yyyymm));
        }
    }

    public static void validateObjectIsNotNull(final Object object, final String errorMessage) {
        if (isNull(object)) {
            throw new SRIllegalArgumentException(errorMessage);
        }
    }

    public static void validateValueIsNotNullOrEmpty(final String value, final String errorMessage) {
        if (isNullOrEmpty(value)) {
            throw new SRIllegalArgumentException(errorMessage);
        }
    }

    public static boolean isEmail(final String email) {
        if (hasText(email)) {
            return matches(REGEX_EMAIL, email);
        }
        return false;
    }

    public static boolean isDateYYYY(final String year) {
        if (hasText(year)) {
            return matches(REGEX_DATE_YYYY, year);
        }
        return false;
    }

    public static boolean isDateMM(final String month) {
        if (hasText(month)) {
            return matches(REGEX_DATE_MM, month);
        }
        return false;
    }

    public static boolean isDateYYMM(final String shortYearMonth) {
        if (hasText(shortYearMonth)) {
            return matches(REGEX_DATE_YYMM, shortYearMonth);
        }
        return false;
    }

    public static boolean isDateYYYYMM(final String date) {
        if (hasText(date)) {
            return matches(REGEX_DATE_YYYYMM, date);
        }
        return false;
    }

    public static boolean isDateYYYYMMDD(final String date) {
        if (hasText(date)) {
            return matches(REGEX_DATE_YYYYMMDD, date);
        }
        return false;
    }

    public static boolean isTimeHHMMSS(final String time) {
        if (hasText(time)) {
            return matches(REGEX_TIME_HH24MMSS, time);
        }
        return false;
    }

    public static boolean isNullOrEmpty(String text) {
        return isNull(text) || text.trim().isEmpty();
    }

    public static <T> boolean isNullOrEmptyList(final List<T> list) {
        return isNull(list) || list.isEmpty();
    }

    private static String makeDefaultErrorMessage(final String errorMessage, final String value) {
        return errorMessage + ERROR_MESSAGE_VALUE_PRE_DEFAULT + value + ERROR_MESSAGE_VALUE_POS_DEFAULT;
    }

    private static String makeDefaultErrorMessage(final String errorMessage, final Long value) {
        return errorMessage + ERROR_MESSAGE_VALUE_PRE_DEFAULT + value + ERROR_MESSAGE_VALUE_POS_DEFAULT;
    }

    private static String makeDefaultErrorMessage(final String errorMessage, final String key, final String value) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append(ERROR_MESSAGE_KEY_PRE);
        errorMessageBuilder.append(key);
        errorMessageBuilder.append(ERROR_MESSAGE_KEY_POST);
        errorMessageBuilder.append(errorMessage);
        errorMessageBuilder.append(ERROR_MESSAGE_VALUE_PRE_DEFAULT);
        errorMessageBuilder.append(value);
        errorMessageBuilder.append(ERROR_MESSAGE_VALUE_POS_DEFAULT);
        return errorMessageBuilder.toString();
    }

    public static boolean isTime(final String time) {
        return matches(REGEX_TIME_HH24MMSS, time);
    }

    public static boolean isMemberNo(final String memberNo) {
        return matches(REGEX_MEMBER_NO, memberNo);
    }

    public static boolean isNumber(final String value) {
        return matches(REGEX_NUMBER, value);
    }

    private interface KSNumber {
        boolean isGreaterThanZero();
        boolean isLessThanOrEqualZero();
    }

    private static class KSLong implements KSNumber {
        private static final long ZERO = 0L;
        private Long value;

        KSLong(final Long value) {
            this.value = value;
        }

        @Override
        public boolean isGreaterThanZero() {
            return isGreaterThan(ZERO);
        }

        @Override
        public boolean isLessThanOrEqualZero() {
            return isLessThanOrEqual(ZERO);
        }

        private boolean isLessThanOrEqual(final Long value) {
            return this.value <= value;
        }

        boolean isGreaterThan(final Long value) {
            return this.value > value;
        }
    }
}
