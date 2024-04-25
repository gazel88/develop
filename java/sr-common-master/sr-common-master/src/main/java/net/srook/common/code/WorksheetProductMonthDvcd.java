package net.srook.common.code;

import java.util.Arrays;
import java.util.List;

import net.srook.common.exception.SRNotFoundException;

import lombok.Getter;

@Getter
public enum WorksheetProductMonthDvcd {
    SE_1("SE0100", "초등 푸르넷", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SE_2("SE0200", "초등 푸르넷", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SE_3("SE0300", "초등 푸르넷", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SE_4("SE0400", "초등 푸르넷", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SE_5("SE0500", "초등 푸르넷", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SE_6("SE0600", "초등 푸르넷", ProductMonthPeriodDvcd.ONE_TO_TWELVE_AND_EIGHTEEN.range),

    SI_1("SI0100", "아이스쿨", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE.range),
    SI_2("SI0200", "아이스쿨", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE.range),
    SI_3("SI0300", "아이스쿨", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE.range),
    SI_4("SI0400", "아이스쿨", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE.range),
    SI_5("SI0500", "아이스쿨", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE.range),
    SI_6("SI0600", "아이스쿨", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE.range),

    SA_1("SA0100", "푸르넷 수학", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SA_2("SA0200", "푸르넷 수학", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SA_3("SA0300", "푸르넷 수학", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SA_4("SA0400", "푸르넷 수학", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SA_5("SA0500", "푸르넷 수학", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SA_6("SA0600", "푸르넷 수학", ProductMonthPeriodDvcd.ONE_TO_TWELVE_AND_EIGHTEEN.range),

    SA_7("SA0130", "푸르넷 수학", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SA_8("SA0230", "푸르넷 수학", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SA_9("SA0330", "푸르넷 수학", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SA_10("SA0430", "푸르넷 수학", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SA_11("SA0530", "푸르넷 수학", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SA_12("SA0630", "푸르넷 수학", ProductMonthPeriodDvcd.ONE_TO_TWELVE_AND_EIGHTEEN.range),

    SL_1("SL1100", "리틀 푸르넷", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SL_2("SL1200", "리틀 푸르넷", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_TO_EIGHTEEN.range),
    SL_3("SL1300", "리틀 푸르넷", ProductMonthPeriodDvcd.ONE_TO_TWELVE_AND_EIGHTEEN.range),

    SO_1("SO0000", "태블릿", ProductMonthPeriodDvcd.ONE.range),

    SN_1("SN0100", "캠핑", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_TO_EIGHTEEN.range),
    SN_2("SN0200", "캠핑", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SN_3("SN0300", "캠핑", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),

    SR_1("SR0100", "독서논술", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SR_2("SR0200", "독서논술", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SR_3("SR0300", "독서논술", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SR_4("SR0400", "독서논술", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SR_5("SR0500", "독서논술", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SR_6("SR0600", "독서논술", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_TO_EIGHTEEN.range),
    SR_7("SR0700", "독서논술", ProductMonthPeriodDvcd.ONE_TO_TWELVE_AND_EIGHTEEN.range),

    SH_0("SH0000", "한글떼기", ProductMonthPeriodDvcd.ONE_TO_SIX.range),

    SG_1("SG0000", "오! 역사논술", ProductMonthPeriodDvcd.ONE_TO_TWELVE.range),

    SF_0("SF0000", "수떼기", ProductMonthPeriodDvcd.ONE_TO_SIX.range),

    SC_A("SC1A00", "한자", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SC_B("SC1B00", "한자", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SC_C("SC1C00", "한자", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SC_D("SC1D00", "한자", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SC_E("SC1E00", "한자", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SC_F("SC1F00", "한자", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SC_G("SC1G00", "한자", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_TO_EIGHTEEN.range),
    SC_H("SC1H00", "한자", ProductMonthPeriodDvcd.ONE_TO_TWELVE_AND_EIGHTEEN.range),
    SC_I("SC1I00", "한자", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),

    SB_00("SB0000", "잉글리시버디 비기너", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),

    SB_11("SB1100", "잉글리시버디 익스", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SB_12("SB1200", "잉글리시버디 익스", ProductMonthPeriodDvcd.ONE_TO_SIX.range),
    SB_13("SB1300", "잉글리시버디 익스", ProductMonthPeriodDvcd.ONE_TO_SIX.range),
    SB_14("SB1400", "잉글리시버디 익스", ProductMonthPeriodDvcd.ONE_TO_SIX.range),

    SB_1A("SB1A00", "잉글리시버디 프라임", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SB_1B("SB1B00", "잉글리시버디 프라임", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SB_1C("SB1C00", "잉글리시버디 프라임", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SB_1D("SB1D00", "잉글리시버디 프라임", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SB_1E("SB1E00", "잉글리시버디 프라임", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SB_1F("SB1F00", "잉글리시버디 프라임", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    SB_1G("SB1G00", "잉글리시버디 프라임", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),

    NM_00("NM0000", "중등 종합 패키지", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),

    NM_01("NM0100", "중등 1학년 패키지", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    NM_02("NM0200", "중등 2학년 패키지", ProductMonthPeriodDvcd.ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN.range),
    NM_03("NM0300", "중등 3학년 패키지", ProductMonthPeriodDvcd.ONE_TO_TWELVE_AND_EIGHTEEN.range),


    NONE("", "",null);

    private final String productCd;
    private final String productName;
    private final List<Integer> firstPeriodRange;

    WorksheetProductMonthDvcd(String productCd, String productName, List<Integer> firstPeriodRange) {
        this.productCd = productCd;
        this.productName = productName;
        this.firstPeriodRange = firstPeriodRange;

    }

    public static List<Integer> findWorksheetProductMonth(final String productCd) {
        return Arrays.stream(values())
                .filter(cd -> cd.productCd.equals(productCd))
                .map(cd -> cd.firstPeriodRange)
                .findFirst()
                .orElseThrow(() -> new SRNotFoundException("상품 코드 조회 결과가 없습니다."));

    }

}
