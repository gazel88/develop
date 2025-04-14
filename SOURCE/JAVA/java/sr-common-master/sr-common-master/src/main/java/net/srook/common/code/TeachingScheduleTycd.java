package net.srook.common.code;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum TeachingScheduleTycd {
    SL0_2_29100_OLD("SL", "2", 29100, "주4회 50분"),
    SL0_1_29100_OLD("SL", "1", 29100, "주1회 50분"),

    SL0_2_37500("SL", "2", 37500, "주4회 50분"),
    SL0_1_37500("SL", "1", 37500, "주1회 50분"),
    SL0_2_45000("SL", "2", 45000, "주5회 50분"),

    SE0_2_29100_OLD("SE", "2", 29100, "주4회 50분"),
    SE0_1_29100_OLD("SE", "1", 29100, "주1회 50분"),

    SE0_2_37500("SE", "2", 37500, "주4회 50분"),
    SE0_1_37500("SE", "1", 37500, "주1회 50분"),
    SE0_2_45000("SE", "2", 45000, "주5회 50분"),

    SR0_2_15900_OLD("SR", "2", 15900, "주1회 40분"),
    SR0_1_15900_OLD("SR", "1", 15900, "주1회 30분"),
    SR0_2_18000_OLD("SR", "2", 18000, "주2회 30분"),

    SR0_2_19500("SR", "2", 19500, "주1회 40분"),
    SR0_1_19500("SR", "1", 19500, "주1회 30분"),
    SR0_2_22500("SR", "2", 22500, "주2회 30분"),
    SR0_2_27000("SR", "2", 27000, "주2회 50분"),

    SA0_2_12000_OLD("SA", "2", 12000, "주1회 50분"),

    SA0_2_15000("SA", "2", 15000, "주1회 50분"),
    SA0_2_21000("SA", "2", 21000, "주2회 50분"),
    SA0_1_21000("SA", "1", 21000, "주1회 50분"),
    SA0_2_29100("SA", "2", 29100, "주4회 50분"),

    SC0_2_9000("SC", "2", 9000, "주1회 30분 / 주3회 10분"),
    SC0_1_9000("SC", "1", 9000, "주1회 20분"),
    SC0_2_10500("SC", "2", 10500, "주1회 40분 / 주4회 10분"),
    SC0_1_10500("SC", "1", 10500, "주1회 30분"),
    SC0_2_12000("SC", "2", 12000, "주1회 50분 / 주5회 10분"),
    SC0_1_12000("SC", "1", 12000, "주1회 40분"),

    SB0_2_39000("SB", "2", 39000, "주5회 50분 / 주2회 120분"),
    SB0_1_39000("SB", "1", 39000, "주1회 60분"),
    SB0_2_48000("SB", "2", 48000, "주5회 60분"),
    SB0_1_48000("SB", "1", 48000, "주1회 70분"),
    SB0_2_57000("SB", "2", 57000, "주5회 70분"),

    SG0_2_21000("SG", "2", 21000, "주2회 40분"),
    SG0_1_21000("SG", "1", 21000, "주1회 30분"),
    SG0_2_39000("SG", "2", 39000, "주2회 50분"),

    SH_2_21000("SH","2",21000, "주2회 40분"),
    SH_1_21000("SH","1",21000, "주1회 30분"),
    SH_2_27000("SH","2",27000, "주2회 50분"),

    SF_2_21000("SH","2",21000, "주2회 40분"),
    SF_1_21000("SH","1",21000, "주1회 30분"),
    SF_2_27000("SH","2",27000, "주2회 50분"),

    NM0_2_57000("NM", "2", 48000, "주3회 90분"),
    NM0_1_57000("NM", "1", 48000, "주1회 90분"),
    NM0_2_60000("NM", "2", 60000, "주4회 90분"),
    NM0_1_60000("NM", "1", 60000, "주2회 60분"),
    NM0_2_72000("NM", "2", 72000, "주5회 90분"),
    NM0_1_72000("NM", "1", 72000, "주3회 50분"),
    NONE("None", "None", 0, "");
    private final String productCd;
    private final String teachingTycd;
    private final int price;
    private final String scheduleComment;

    TeachingScheduleTycd(final String productCd, final String teachingTycd, final int price, final String scheduleComment) {
        this.productCd = productCd;
        this.teachingTycd = teachingTycd;
        this.price = price;
        this.scheduleComment = scheduleComment;
    }

    public static TeachingScheduleTycd findTeachingSchedule(final String productCd, final String teachingTycd, final int price) {
        return Arrays.stream(values())
                .filter(cd -> cd.productCd.equals(productCd))
                .filter(cd -> cd.teachingTycd.equals(teachingTycd))
                .filter(cd -> cd.price == price)
                .findFirst()
                .orElse(NONE);
    }
}
