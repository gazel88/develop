package net.srook.common.code;

import lombok.Getter;

@Getter
public enum SalesStcd {
    SS_01("01", "정상"),
    SS_11("11", "유보"),
    SS_12("12", "연체"),
    SS_31("31", "계약취소"),
    SS_32("32", "취소예정"),
    SS_33("33", "취소"),
    SS_34("34", "경리취소"),
    SS_91("91", "전금만료"),
    SS_92("92", "만료"),
    ;

    private final String code;
    private final String description;

    SalesStcd(final String code, final String description) {
        this.code = code;
        this.description = description;
    }
}
