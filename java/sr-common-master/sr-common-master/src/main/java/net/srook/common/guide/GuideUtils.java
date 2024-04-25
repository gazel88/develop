package net.srook.common.guide;

import static net.srook.common.utils.DateUtils.extractDay;
import static net.srook.common.utils.ValidateUtils.validateValueTypeOfYearMonthDay;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GuideUtils {

    public static double calculateGuideMonth(final String guideStartDate, final int guideMonth) {
        validateValueTypeOfYearMonthDay(guideStartDate);
        return calculateDetailFirstMonthOf(guideStartDate) + calculateRemainingMonth(guideMonth);
    }

    private static double calculateDetailFirstMonthOf(final String guideStartDate) {
        return GuideMonthRateDvcd.getRateOf(extractDay(guideStartDate));
    }

    private static int calculateRemainingMonth(final int guideMonth) {
        return guideMonth - 1;
    }
}
