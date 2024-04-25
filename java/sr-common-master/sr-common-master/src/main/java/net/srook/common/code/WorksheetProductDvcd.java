package net.srook.common.code;

import java.util.Arrays;
import java.util.List;

import net.srook.common.exception.SRNotFoundException;

import lombok.Getter;

@Getter
public enum WorksheetProductDvcd {
    SE_A("SE0X00A", "초등 푸르넷", ProductDvcd.WORKSHEET, PeriodTypeDvcd.GRADE, PeriodDvcd.ONE_TO_SIX.range, null, 38000),
    SA_A("SA0X30A", "푸르넷 수학", ProductDvcd.WORKSHEET, PeriodTypeDvcd.GRADE, PeriodDvcd.ONE_TO_SIX.range, null, 23000),
    SG_A("SG0X00A", "오! 역사논술", ProductDvcd.WORKSHEET, PeriodTypeDvcd.LEVEL_SEQUENCE, PeriodDvcd.ZERO.range, PeriodDvcd.ONE_TO_TWELVE.range, 15000),
    SL_A("SL1X00A", "리틀 푸르넷", ProductDvcd.WORKSHEET, PeriodTypeDvcd.LEVEL_SEQUENCE, PeriodDvcd.ONE_TO_THREE.range, PeriodDvcd.ONE_TO_TWELVE.range, 38000),
    SL_Z("SL1X00Z", "리틀 푸르넷 (형제)", ProductDvcd.WORKSHEET, PeriodTypeDvcd.LEVEL_SEQUENCE, PeriodDvcd.ONE_TO_THREE.range, PeriodDvcd.ONE_TO_TWELVE.range, 38000),
    SR_A("SR0X00B", "독서논술", ProductDvcd.WORKSHEET, PeriodTypeDvcd.LEVEL_SEQUENCE, PeriodDvcd.ONE_TO_SEVEN.range, PeriodDvcd.ONE_TO_TWELVE.range, 34000),
    SR_Z("SR0X00Z", "독서논술 (형제)", ProductDvcd.WORKSHEET, PeriodTypeDvcd.LEVEL_SEQUENCE, PeriodDvcd.ONE_TO_SEVEN.range, PeriodDvcd.ONE_TO_TWELVE.range, 34000),
    SH_A("SH0X00A", "푸르넷 한글떼기", ProductDvcd.WORKSHEET, PeriodTypeDvcd.SEQUENCE, PeriodDvcd.ZERO.range, PeriodDvcd.ONE_TO_SIX.range, 15000),
    SF_A("SF0X00A", "푸르넷 수떼기", ProductDvcd.WORKSHEET, PeriodTypeDvcd.SEQUENCE, PeriodDvcd.ZERO.range, PeriodDvcd.ONE_TO_SIX.range, 15000),
    SC_A("SC1X00A", "푸르넷 한자", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_SEQUENCE, PeriodDvcd.A_TO_I.range, PeriodDvcd.ONE_TO_SIX.range, 18000),
    SB01_A("SB0X00A", "잉글리시버디 비기너", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_STEP, PeriodDvcd.ZERO.range, PeriodDvcd.ONE_TO_SIX.range, 45000),
    SB11_A("SB1100A", "잉글리시버디 익스", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_STEP, PeriodDvcd.ONE_TO_FOUR.range, PeriodDvcd.ONE_TO_SIX.range, 45000),
    SB1A_A("SB1A00A", "잉글리시버디 프라임", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_STEP, PeriodDvcd.A_TO_G.range, PeriodDvcd.ONE_TO_SIX.range, 45000),
    SB0100Z("SB0X00Z", "잉글리시버디 비기너 (형제)", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_STEP, PeriodDvcd.ZERO.range, PeriodDvcd.ONE_TO_SIX.range, 45000),
    SB1100Z("SB1100Z", "잉글리시버디 익스 (형제)", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_STEP, PeriodDvcd.ONE_TO_FOUR.range, PeriodDvcd.ONE_TO_SIX.range, 45000),
    SB1A00Z("SB1A00Z", "잉글리시버디 프라임 (형제)", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_STEP, PeriodDvcd.A_TO_G.range, PeriodDvcd.ONE_TO_SIX.range, 45000),
    SB01_AB("SB0X00AB", "잉글리시버디 비기너 (교재 + 웹)", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_STEP, PeriodDvcd.ZERO.range, PeriodDvcd.ONE_TO_SIX.range, 45000),
    SB11_AB("SB1100AB", "잉글리시버디 익스 (교재 + 웹)", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_STEP, PeriodDvcd.ONE_TO_FOUR.range, PeriodDvcd.ONE_TO_SIX.range, 45000),
    SB1A_AB("SB1A00AB", "잉글리시버디 프라임 (교재 + 웹)", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_STEP, PeriodDvcd.A_TO_G.range, PeriodDvcd.ONE_TO_SIX.range, 45000),
    SB01_ZB("SB0X00ZB", "잉글리시버디 비기너 (형제, 교재 + 웹)", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_STEP, PeriodDvcd.ZERO.range, PeriodDvcd.ONE_TO_SIX.range, 45000),
    SB11_ZB("SB1100ZB", "잉글리시버디 익스 (형제, 교재 + 웹)", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_STEP, PeriodDvcd.ONE_TO_FOUR.range, PeriodDvcd.ONE_TO_SIX.range, 45000),
    SB1A_ZB("SB1A00ZB", "잉글리시버디 프라임 (형제, 교재 + 웹)", ProductDvcd.WORKSHEET, PeriodTypeDvcd.COURSE_STEP, PeriodDvcd.A_TO_G.range, PeriodDvcd.ONE_TO_SIX.range, 45000),
    SN1A("SN0X00A", "캠핑", ProductDvcd.WORKSHEET, PeriodTypeDvcd.LEVEL_SEQUENCE, PeriodDvcd.ONE_TWO.range, PeriodDvcd.ONE_TO_TWELVE.range, 64000),
    SO1A("SO0X00A", "캠핑 태블릿", ProductDvcd.WORKSHEET, PeriodTypeDvcd.NONE, PeriodDvcd.ZERO.range, null, 399000),
    NM01_A("NM0X00A", "중등 최강 BEST 패키지", ProductDvcd.WORKSHEET, PeriodTypeDvcd.GRADE, PeriodDvcd.ONE_TO_THREE.range, null, 66000),
    NM01_B("NM0X00B", "중등 ALL100 패키지", ProductDvcd.WORKSHEET, PeriodTypeDvcd.GRADE, PeriodDvcd.ONE_TO_THREE.range, null, 54000),
    NM_A("NM0000A", "중등 종합 패키지", ProductDvcd.WORKSHEET, PeriodTypeDvcd.NONE, PeriodDvcd.ZERO.range, null, 78000),

    NONE("", "", null, PeriodTypeDvcd.NONE, null, null, 0);

    private final String productCd;
    private final String productName;
    private final ProductDvcd productDvcd;
    private final PeriodTypeDvcd periodTypeCd;
    private final List<String> firstPeriodRange;
    private final List<String> secondPeriodRange;
    private final int productPrice;

    WorksheetProductDvcd(String productCd, String productName, ProductDvcd productDvcd, PeriodTypeDvcd periodTypeCd, List<String> firstPeriodRange,
                         List<String> secondPeriodRange, int productPrice) {
        this.productCd = productCd;
        this.productName = productName;
        this.productDvcd = productDvcd;
        this.periodTypeCd = periodTypeCd;
        this.firstPeriodRange = firstPeriodRange;
        this.secondPeriodRange = secondPeriodRange;
        this.productPrice = productPrice;
    }

    public static WorksheetProductDvcd findWorksheetProductName(final String productCd) {
        return Arrays.stream(values())
                .filter(cd -> cd.productCd.equals(productCd))
                .findFirst()
                .orElse(NONE);
    }
    public static String replaceWorksheetProductCd(String replaceProductCd,String replaceValue) {
        String productCd = Arrays.stream(values())
                .filter(cd -> cd.productCd.equals(replaceProductCd))
                .findFirst()
                .orElseThrow(() -> new SRNotFoundException("조회 결과가 없습니다.")).getProductCd();
        return productCd.substring(0,3) + replaceValue + productCd.substring(4);
    }

}
