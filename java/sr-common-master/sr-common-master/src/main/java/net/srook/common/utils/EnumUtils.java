package net.srook.common.utils;

import static net.srook.common.utils.ValidateUtils.isNullOrEmpty;

import net.srook.common.exception.SRIllegalArgumentException;
import net.srook.common.exception.SRIllegalStateException;
import net.srook.common.exception.SRNotFoundCodeException;

public class EnumUtils {

    private EnumUtils() {
        throw new SRIllegalStateException("Utility class");
    }

    public static <T extends Enum<T>> T findCodeOrThrow(final Class<T> enumType, final String name) {
        validateRequiredCodeName(name);
        try {
            return Enum.valueOf(enumType, name);
        } catch (IllegalArgumentException exception) {
            throw new SRNotFoundCodeException("유효하지 않은 코드값 입니다. [Type : " + enumType.getSimpleName()
                    + ", Input Name : \"" + name + "\"]");
        }
    }

    private static void validateRequiredCodeName(final String name) {
        if (isNullOrEmpty(name)) {
            throw new SRIllegalArgumentException("name은 필수 입력 항목입니다.");
        }
    }
}
