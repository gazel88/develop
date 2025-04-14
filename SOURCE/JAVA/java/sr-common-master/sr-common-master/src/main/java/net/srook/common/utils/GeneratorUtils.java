package net.srook.common.utils;

import static java.lang.Math.random;
import static java.lang.Math.round;

import java.util.Random;

import net.srook.common.exception.SRIllegalStateException;

public class GeneratorUtils {
    private static final long MAKE_TO_LONG_CONST = 100_000_000_000_000_000L;
    private static final int FIX_INDEX_COUNT_FOR_LENGTH = 1;
    private static final char[] SOURCE_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final int[] SOURCE_NUMBERS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    private GeneratorUtils() {
        throw new SRIllegalStateException("Utility class");
    }

    public static String makeRandomPassword(final int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(SOURCE_CHARS[getRandomIndex(SOURCE_CHARS.length)]);
        }
        return stringBuilder.toString();
    }

    public static String makeRandomNumber(final int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(SOURCE_NUMBERS[getRandomIndex(SOURCE_NUMBERS.length)]);
        }
        return stringBuilder.toString();
    }

    private static int getRandomIndex(final int length) {
        return new Random(getRandomSeed())
                .nextInt(getMaxIndexFrom(length));
    }

    private static long getRandomSeed() {
        return round(random() * MAKE_TO_LONG_CONST);
    }

    private static int getMaxIndexFrom(final int length) {
        return length - FIX_INDEX_COUNT_FOR_LENGTH;
    }
}
