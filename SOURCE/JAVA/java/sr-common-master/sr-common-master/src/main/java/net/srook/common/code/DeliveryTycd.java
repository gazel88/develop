package net.srook.common.code;

import java.util.Arrays;

import net.srook.common.exception.SRIllegalArgumentException;

import lombok.Getter;

@Getter
public enum DeliveryTycd {
    DT_1("1", "정규"),
    DT_2("2", "소급"),
    DT_3("3", "A/S"),
    DT_4("4", "단체"),
    DT_6("6", "운영"),
    DT_7("7", "낱권"),
    DT_8("8", "무료"),
    ;

    private final String code;
    private final String description;

    DeliveryTycd(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static boolean isDeliveryTycd(final String deliveryTycd) {
        return Arrays.stream(values()).anyMatch(code -> code.code.equals(deliveryTycd));
    }

    public static void validateDeliveryTycd(final String deliveryTycd) {
        if (Arrays.stream(values()).noneMatch(code -> code.code.equals(deliveryTycd))) {
            throw new SRIllegalArgumentException("유효한 배송유형 코드가 아닙니다. Value : [{deliveryTycd}]"
                    .replace("{deliveryTycd}", deliveryTycd));
        }
    }
}
