package net.srook.common.code;

import java.util.Arrays;

import net.srook.common.exception.SRIllegalArgumentException;

import lombok.Getter;

@Getter
public enum GuideDvcd {
    GD_0("0", "스쿨"),
    GD_1("1", "방문"),
    GD_2("2", "공부방"),
    GD_3("3", "크니크니"),
    ;

    private final String code;
    private final String description;

    GuideDvcd(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static boolean isGuideDvcd(final String guideDvcd) {
        return Arrays.stream(values()).anyMatch(code -> code.code.equals(guideDvcd));
    }

    public static void validateGuideDvcd(final String guideDvcd) {
        if (Arrays.stream(values()).noneMatch(code -> code.code.equals(guideDvcd))) {
            throw new SRIllegalArgumentException("유효한 지도구분 코드가 아닙니다. Value : [{guideDvcd}]"
                    .replace("{guideDvcd}", guideDvcd));
        }
    }
}
