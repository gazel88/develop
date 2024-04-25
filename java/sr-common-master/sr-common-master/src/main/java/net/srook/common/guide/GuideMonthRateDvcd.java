package net.srook.common.guide;

import static java.util.regex.Pattern.matches;

import java.util.Arrays;

import net.srook.common.exception.SRIllegalArgumentException;
import net.srook.common.exception.SRNotFoundException;

import lombok.Getter;

@Getter
public enum GuideMonthRateDvcd {
    R_100("01", "07", 1.0), R_75("08", "14", 0.75), R_50("15", "22", 0.5), R_25("23", "31", 0.25);

    public static final String REGEX_DAY = "^(0[1-9]|[12][0-9]|3[0-1])$";
    private final String startDat;
    private final String endDay;
    private final double rate;

    GuideMonthRateDvcd(final String startDay, final String endDay, final double rate) {
        this.startDat = startDay;
        this.endDay = endDay;
        this.rate = rate;
    }

    public static double getRateOf(final String targetDay) {
        if (!matches(REGEX_DAY, targetDay)) {
            throw new SRIllegalArgumentException("유효한 일자(DD) 양식이 아닙니다.");
        }
        return Arrays.stream(values())
                .filter(dvcd -> isBetween(dvcd.getStartDat(), dvcd.getEndDay(), targetDay))
                .findFirst()
                .orElseThrow(() -> new SRNotFoundException("유효한 일자가 아닙니다."))
                .getRate();
    }

    private static boolean isBetween(final String startDat, final String endDay, final String targetDay) {
        return Integer.parseInt(startDat) <= Integer.parseInt(targetDay)
                && Integer.parseInt(endDay) >= Integer.parseInt(targetDay);
    }
}
