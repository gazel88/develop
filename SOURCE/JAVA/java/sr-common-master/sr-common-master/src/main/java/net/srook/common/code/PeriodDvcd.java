package net.srook.common.code;


import java.util.List;


public enum PeriodDvcd {

    ONE(List.of("1")),
    ONE_TWO(List.of("1","2")),
    ONE_TO_THREE(List.of("1", "2", "3")),
    ONE_TO_FOUR(List.of("1", "2", "3", "4")),

    ONE_TO_SIX(List.of("1", "2", "3", "4", "5", "6")),

    ONE_TO_SEVEN(List.of("1", "2", "3", "4", "5", "6", "7")),

    ONE_TO_TWELVE(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")),

    A_TO_G(List.of("A","B","C","D","E","F","G")),

    A_TO_I(List.of("A","B","C","D","E","F","G","H","I")),
    ZERO(List.of("0"));

    List<String> range;

    PeriodDvcd(List<String> range) {

        this.range = range;

    }

}