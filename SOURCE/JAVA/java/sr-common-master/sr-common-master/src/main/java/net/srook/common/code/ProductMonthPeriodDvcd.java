package net.srook.common.code;


import java.util.List;


public enum ProductMonthPeriodDvcd {

    ONE(List.of(1)),
    ONE_TO_SIX(List.of(1, 2, 3, 4, 5, 6)),
    ONE_TO_TWELVE(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)),
    ONE_TO_SIX_AND_TWELVE(List.of(1, 2, 3, 4, 5, 6, 12)),
    ONE_TO_SIX_AND_TWELVE_AND_EIGHTEEN(List.of(1, 2, 3, 4, 5, 6, 12, 18)),
    ONE_TO_TWELVE_AND_EIGHTEEN(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 18)),
    ONE_TO_SIX_AND_TWELVE_TO_EIGHTEEN(List.of(1, 2, 3, 4, 5, 6, 12, 13, 14, 15, 16, 17, 18)),
    ONE_TO_SIX_AND_TEN_TO_EIGHTEEN(List.of(1, 2, 3, 4, 5, 6, 10, 11, 12, 13, 14, 15, 16, 17, 18)),
    NONE(List.of(0));

    List<Integer> range;

    ProductMonthPeriodDvcd(List<Integer> range) {

        this.range = range;

    }

}