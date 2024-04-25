package net.srook.common.guide;

import static net.srook.common.guide.GuideUtils.calculateGuideMonth;
import static net.srook.common.utils.ValidateUtils.validateValueTypeOfYearMonthDay;

import net.srook.common.exception.SRIllegalArgumentException;
import net.srook.common.exception.SRIllegalStateException;

public class GuideAmountCalculator {
    public static final double COMPANY_RATE = 0.3;
    public static final double STUDY_ROOM_RATE = 0.7;
    private static final int FIXED_VALUE_A_HUNDRED_PERCENT = 100;
    private static final int COMPANY_PERCENT = 30;
    private static final int STUDY_ROOM_PERCENT = 70;
    private final int totalAmount;

    private GuideAmountCalculator(final int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public static GuideAmountCalculator of(final String guideStartDate, final int guideAmountPerMonth, final int contractGuideMonthTerm) {
        validateValueTypeOfYearMonthDay(guideStartDate);
        validateParams(guideAmountPerMonth, contractGuideMonthTerm);
        final GuideAmountCalculator guideAmountCalculator = new GuideAmountCalculator(makeTotalGuideAmount(guideStartDate, contractGuideMonthTerm, guideAmountPerMonth));
        guideAmountCalculator.validateAmount();
        return guideAmountCalculator;
    }
    public static GuideAmountCalculator of(final int totalAmount) {
        if (totalAmount < 0) {
            throw new SRIllegalArgumentException("총 금액은 필수 입력 항목 입니다.");
        }
        return new GuideAmountCalculator(totalAmount);
    }

    private static void validateParams(final int guideAmount, final int contractGuideMonthTerm) {
        if (guideAmount <= 0) {
            throw new SRIllegalArgumentException("지도금액은 0보다 작을 수 없습니다.");
        }
        if (contractGuideMonthTerm <= 0) {
            throw new SRIllegalArgumentException("계약 지도개월 수는 0보다 작을 수 없습니다.");
        }
    }

    private void validateAmount() {
        if (this.getTotalGuideAmountForStudyRoom() + this.getTotalGuideAmountForCompany() != this.getTotalGuideAmount()) {
            throw new SRIllegalStateException("공부방 비율 금액과 본사 비율 금액의 합이 총 비용과 일치하지 않습니다. 공부방 비용 확인 바랍니다. - 전체 비용: " + this.totalAmount);
        }
    }

    private static int makeTotalGuideAmount(final String guideStartDate, final int contractGuideMonthTerm, final int guideAmountPerMonth) {
        final int detailGuideMonth = (int) (calculateGuideMonth(guideStartDate, contractGuideMonthTerm) * FIXED_VALUE_A_HUNDRED_PERCENT);
        return guideAmountPerMonth * detailGuideMonth / FIXED_VALUE_A_HUNDRED_PERCENT;
    }
    public int getTotalGuideAmount() {
        return this.totalAmount;
    }

    public int getTotalGuideAmountForCompany() {
        return this.totalAmount * COMPANY_PERCENT / FIXED_VALUE_A_HUNDRED_PERCENT;
    }

    public int getTotalGuideAmountForStudyRoom() {
        return this.totalAmount * STUDY_ROOM_PERCENT / FIXED_VALUE_A_HUNDRED_PERCENT;
    }
}
