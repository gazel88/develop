package net.srook.common.code;

import lombok.Getter;

@Getter
public enum GuideHistoryStcd {
    GHS_0("0", "지도취소"),
    GHS_1("1", "신규"),
    GHS_2("2", "복회"),
    GHS_3("3", "인수"),
    GHS_4("4", "판매변경"),
    GHS_5("5", "회원수변경"),
    GHS_6("6", "회비변경"),
    GHS_7("7", "휴회"),
    GHS_8("8", "인정휴회"),
    GHS_9("9", "인계"),
    ;

    private final String code;
    private final String description;

    GuideHistoryStcd(final String code, final String description) {
        this.code = code;
        this.description = description;
    }
}
