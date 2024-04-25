package net.srook.common.utils;

import static java.time.LocalTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

import net.srook.common.exception.SRIllegalArgumentException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtils {
    private static final String ERROR_MESSAGE_WRONG_TIME_FORMAT = "시간 형식을 확인해주세요.";
    private static final String DEFAULT_TIME_PATTERN = "HHmmss";

    public static String extractHour(final String time) {
        if (ValidateUtils.isTimeHHMMSS(time)) {
            return time.substring(0, 2);
        }
        throw new SRIllegalArgumentException(ERROR_MESSAGE_WRONG_TIME_FORMAT);
    }

    public static String extractMinute(final String time) {
        if (ValidateUtils.isTimeHHMMSS(time)) {
            return time.substring(2, 4);
        }
        throw new SRIllegalArgumentException(ERROR_MESSAGE_WRONG_TIME_FORMAT);
    }

    public static String extractSecond(final String time) {
        if (ValidateUtils.isTimeHHMMSS(time)) {
            return time.substring(4, 6);
        }
        throw new SRIllegalArgumentException(ERROR_MESSAGE_WRONG_TIME_FORMAT);
    }

    public static boolean isHHMMSS(final String time) {
        return ValidateUtils.isTimeHHMMSS(time);
    }

    public static String formatTime(final String time, final String fromPattern, final String toPattern) {
        if (ValidateUtils.isTimeHHMMSS(time)) {
            return parse(time, ofPattern(fromPattern))
                    .format(ofPattern(toPattern));
        }
        throw new SRIllegalArgumentException(ERROR_MESSAGE_WRONG_TIME_FORMAT);
    }

    public static String formatTime(final String time, final String toPattern) {
        return formatTime(time, DEFAULT_TIME_PATTERN, toPattern);
    }
}
