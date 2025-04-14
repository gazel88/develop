package net.srook.common.utils;

import static java.time.Instant.ofEpochSecond;
import static java.time.LocalDateTime.ofInstant;
import static java.time.LocalDateTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.time.temporal.ChronoUnit.*;
import static java.util.TimeZone.getDefault;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import net.srook.common.exception.SRIllegalArgumentException;
import net.srook.common.exception.SRIllegalStateException;

public class DateUtils {
    private static final String FORMAT_24HOUR_MINUTE_SECOND = "HHmmss";
    private static final String FORMAT_24HOUR_MINUTE_SECOND_STRING = "HH:mm:ss";
    private static final String FORMAT_YEAR_MONTH_DAY = "yyyyMMdd";
    private static final String FORMAT_YEAR_MONTH = "yyyyMM";
    private static final String DEFAULT_DAY = "01";
    private static final int FIXED_PERIOD = 1;
    private static final String START_YEAR_MONTH = "시작년월";
    private static final String END_YEAR_MONTH = "종료년월";
    public static final String FORMAT_YEAR_MONTH_DAY_24HOUR_MINUTE_FRACTION_OF_SECOND = "yyyyMMddHHmmSSS";
    public static final String FORMAT_YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND = "yyyyMMddHHmmss";
    public static final String ERROR_MESSAGE_WRONG_DATE_FORMAT = "날짜 형식을 확인해주세요.";
    public static final int CORRECTION_MINUTE_SECOND_TIME = 60;
    public static final int INDEX_FIRST_DAY = 1;

    private DateUtils() {
        throw new SRIllegalStateException("Utility class");
    }

    public static String getTodayToYYYYMMDD(){
        return getNowDate().format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    public static String getTodayToYYYYMM(){
        return getNowDate().format(DateTimeFormatter.ofPattern(FORMAT_YEAR_MONTH));
    }

    private static LocalDate getNowDate() {
        return LocalDate.now();
    }

    public static String getNowDateToString() {
        return getNowDateToString(FORMAT_YEAR_MONTH_DAY);
    }

    public static String getNowDateTimeToString() {
        return getNowDateToString(FORMAT_YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND);
    }

    public static String getNowTimeToString() {
        return getNowDateToString(FORMAT_24HOUR_MINUTE_SECOND);
    }

    public static DayOfWeek getDayOfWeek(final String date) {
        ValidateUtils.validateValueTypeOfYearMonthDay(date);
        return toLocalDate(date).getDayOfWeek();
    }

    public static String getKoreanDayOfWeek(final String date) {
        ValidateUtils.validateValueTypeOfYearMonthDay(date);
        return toLocalDate(date)
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.KOREAN);
    }

    public static String getNowDateFractionSecondToString() {
        return getNowDateToString(FORMAT_YEAR_MONTH_DAY_24HOUR_MINUTE_FRACTION_OF_SECOND);
    }

    public static String getNowDateToString(final String pattern) {
        return getNowDateTime()
                .format(ofPattern(pattern));
    }

    public static String transferTimeFormat(final String hhmmss, final String pattern) {
        return toLocalTime(hhmmss)
                .format(ofPattern(pattern));
    }

    public static String transferTimeFormat(final String hhmmss) {
        return toLocalTime(hhmmss)
                .format(ofPattern(FORMAT_24HOUR_MINUTE_SECOND_STRING));
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static String toStringDate(LocalDateTime localDateTime) {
        return localDateTime.format(ofPattern(FORMAT_YEAR_MONTH_DAY));
    }

    public static String toStringDate(LocalDate localDate) {
        return localDate.format(ofPattern(FORMAT_YEAR_MONTH_DAY));
    }

    public static String toStringDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(ofPattern(FORMAT_YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND));
    }

    public static String toStringDateTime(final LocalDateTime localDateTime, final String format) {
        return localDateTime.format(ofPattern(format));
    }

    public static LocalDate toLocalDate(String yyyyMMdd) {
        ValidateUtils.validateValueTypeOfYearMonthDay(yyyyMMdd);
        return LocalDate.parse(yyyyMMdd, ofPattern(FORMAT_YEAR_MONTH_DAY));
    }

    public static LocalDateTime toLocalDateTime(final String dateTime) {
        return parse(dateTime, ofPattern(FORMAT_YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND));
    }

    public static LocalDateTime toLocalDateTime(final String dateTime, final String pattern) {
        return parse(dateTime, ofPattern(pattern));
    }

    public static String formatDateTime(final String dateTime, final String fromPattern, final String toPattern) {
        return parse(dateTime, ofPattern(fromPattern))
                .format(ofPattern(toPattern));
    }

    public static LocalTime toLocalTime(String hhmmss) {
        validateValueTypeOfTime(hhmmss);
        return LocalTime.parse(hhmmss, ofPattern(FORMAT_24HOUR_MINUTE_SECOND));
    }

    private static void validateValueTypeOfTime(final String hhmmss) {
        if (!ValidateUtils.isTimeHHMMSS(hhmmss)) {
            throw new SRIllegalArgumentException("시간 유형의 데이터가 아닙니다. value : " + hhmmss);
        }
    }

    public static String getDateAddedByMonthsFromNow(int months) {
        return getNowDateTime()
                .plusMonths(months)
                .format(ofPattern(FORMAT_YEAR_MONTH_DAY));
    }

    public static String getDateAddedByMonthsFrom(final String date, final int months) {
        return toLocalDate(date)
                .plusMonths(months)
                .format(ofPattern(FORMAT_YEAR_MONTH_DAY));
    }

    public static String getDateAddedByYearsFrom(final String date, final int years) {
        return toLocalDate(date)
                .plusYears(years)
                .format(ofPattern(FORMAT_YEAR_MONTH_DAY));
    }

    public static String getDateAddedByDaysFrom(final String date, final int days) {
        return toLocalDate(date)
                .plusDays(days)
                .format(ofPattern(FORMAT_YEAR_MONTH_DAY));
    }

    private static LocalDateTime getNowDateTime() {
        return LocalDateTime.now();
    }

    public static Long calculatePeriodMonth(final String startYearMonth, final String endYearMonth) {
        ValidateUtils.validateValueTypeOfYearMonth(startYearMonth, START_YEAR_MONTH);
        ValidateUtils.validateValueTypeOfYearMonth(endYearMonth, END_YEAR_MONTH);
        return MONTHS.between(toLocalDateForYearMonth(startYearMonth), toLocalDateForYearMonth(endYearMonth))
                + FIXED_PERIOD;
    }

    private static LocalDate toLocalDateForYearMonth(final String yyyyMM) {
        ValidateUtils.validateValueTypeOfYearMonth(yyyyMM);
        return LocalDate.parse(yyyyMM + DEFAULT_DAY, ofPattern(FORMAT_YEAR_MONTH_DAY));
    }

    public static boolean isBetween(final String baseDate, final String startedDate, final String endedDate) {
        return isBetween(toLocalDate(baseDate), toLocalDate(startedDate), toLocalDate(endedDate));
    }

    public static boolean isBetween(final LocalDate baseDate, final LocalDate startedDate, final LocalDate endedDate) {
        return (baseDate.isEqual(startedDate) || baseDate.isAfter(startedDate))
                && (baseDate.isEqual(endedDate) || endedDate.isAfter(baseDate));
    }

    public static String extractYearMonth(final String date) {
        if (ValidateUtils.isDateYYYYMMDD(date) || ValidateUtils.isDateYYYYMM(date) || ValidateUtils.isDateYYYY(date)) {
            return date.substring(0, 6);
        }
        throw new SRIllegalArgumentException(ERROR_MESSAGE_WRONG_DATE_FORMAT);
    }

    public static String extractYear(final String date) {
        if (ValidateUtils.isDateYYYYMMDD(date) || ValidateUtils.isDateYYYYMM(date) || ValidateUtils.isDateYYYY(date)) {
            return date.substring(0, 4);
        }
        throw new SRIllegalArgumentException(ERROR_MESSAGE_WRONG_DATE_FORMAT);
    }

    public static String extractMonth(final String date) {
        if (ValidateUtils.isDateYYYYMMDD(date) || ValidateUtils.isDateYYYYMM(date)) {
            return date.substring(4, 6);
        }
        throw new SRIllegalArgumentException(ERROR_MESSAGE_WRONG_DATE_FORMAT);
    }

    public static String extractDay(final String date) {
        if (ValidateUtils.isDateYYYYMMDD(date)) {
            return date.substring(6, 8);
        }
        throw new SRIllegalArgumentException(ERROR_MESSAGE_WRONG_DATE_FORMAT);
    }

    public static boolean isWithinMinute(final String beforeDateTime, final String afterDateTime, final int minute) {
        return (betweenMinutes(beforeDateTime, afterDateTime) % CORRECTION_MINUTE_SECOND_TIME) < minute;
    }

    public static long betweenSeconds(final String beforeDateTime, final String afterDateTime) {
        return SECONDS.between(toLocalDateTime(beforeDateTime), toLocalDateTime(afterDateTime));
    }

    public static long betweenMinutes(final String beforeDateTime, final String afterDateTime) {
        return MINUTES.between(toLocalDateTime(beforeDateTime), toLocalDateTime(afterDateTime));
    }

    public static long betweenHours(final String beforeDateTime, final String afterDateTime) {
        return HOURS.between(toLocalDateTime(beforeDateTime), toLocalDateTime(afterDateTime));
    }

    public static long betweenDays(final String beforeDate, final String afterDate) {
        return DAYS.between(toLocalDate(beforeDate), toLocalDate(afterDate));
    }

    public static long betweenMonths(final String beforeDate, final String afterDate) {
        return MONTHS.between(toLocalDate(beforeDate), toLocalDate(afterDate));
    }

    public static long betweenYears(final String beforeDate, final String afterDate) {
        return YEARS.between(toLocalDate(beforeDate), toLocalDate(afterDate));
    }

    public static String toStringDateTimeOfEpochSecond(final Long epochSecond) {
        return toStringDateTimeOfEpochSecond(epochSecond, FORMAT_YEAR_MONTH_DAY_24HOUR_MINUTE_SECOND);
    }

    public static String toStringDateTimeOfEpochSecond(final Long epochSecond, final String format) {
        return toStringDateTime(ofInstant(ofEpochSecond(epochSecond), getDefault().toZoneId()), format);
    }

    public static String getEndDateOfMonthFrom(final String date) {
        ValidateUtils.validateValueTypeOfYearMonthDay(date);
        return toStringDate(YearMonth.from(toLocalDate(date)).atEndOfMonth());
    }

    public static String getFirstDateOfMonthFrom(final String date) {
        ValidateUtils.validateValueTypeOfYearMonthDay(date);
        return toStringDate(YearMonth.from(toLocalDate(date)).atDay(INDEX_FIRST_DAY));
    }
}
