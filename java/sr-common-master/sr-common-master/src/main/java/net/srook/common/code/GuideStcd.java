package net.srook.common.code;

import java.util.Arrays;

import net.srook.common.exception.SRIllegalArgumentException;

import lombok.Getter;

@Getter
public enum GuideStcd {
    GS_0("0", "지도취소"),
    GS_1("1", "신규"),
    GS_2("2", "복회"),
    GS_7("7", "휴회"),
    GS_8("8", "인정휴회"),
    ;

    private final String code;
    private final String description;

    GuideStcd(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static boolean isGuideStcd(final String guideStcd) {
        return Arrays.stream(values()).anyMatch(code -> code.code.equals(guideStcd));
    }

    public static void validateGuideStcd(final String guideStcd) {
        if (Arrays.stream(values()).noneMatch(code -> code.code.equals(guideStcd))) {
            throw new SRIllegalArgumentException("유효한 지도상태 코드가 아닙니다. Value : [{guideStcd}]"
                    .replace("{guideStcd}", guideStcd));
        }
    }
}
