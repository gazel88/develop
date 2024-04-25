package net.srook.common.code;

import java.util.Arrays;
import java.util.Comparator;

import lombok.Getter;

@Getter
public enum KoreanDvcd {
    KC_01_GA('가'),
    KC_02_NA('나'),
    KC_03_DA('다'),
    KC_04_RA('라'),
    KC_05_MA('마'),
    KC_06_BA('바'),
    KC_07_SA('사'),
    KC_08_A('아'),
    KC_09_JA('자'),
    KC_10_CHA('차'),
    KC_11_KA('카'),
    KC_12_TA('타'),
    KC_13_PA('파'),
    KC_14_HA('하'),
    KC_99_END('힣');

    private final int word;

    KoreanDvcd(final int word) {
        this.word = word;
    }

    public static KoreanDvcd findKoreanDvcdBy(final char firstWord) {
        return Arrays.stream(values())
                .sorted(Comparator.reverseOrder())
                .filter(dvcd -> dvcd.getWord() < firstWord)
                .findFirst()
                .orElse(KC_99_END);
    }
}
