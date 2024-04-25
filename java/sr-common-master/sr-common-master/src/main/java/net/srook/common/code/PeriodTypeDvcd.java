package net.srook.common.code;


import lombok.Getter;

@Getter
public enum PeriodTypeDvcd {

    GRADE_MONTH("학년", "월"),

    LEVEL_SEQUENCE("단계", "호"),

    COURSE_SEQUENCE("코스", "호"),

    SEQUENCE(null, "호"),

    COURSE_STEP("코스", "스텝"),

    GRADE("학년", null),

    NONE(null, null);


    private final String firstPeriod;

    private final String secondPeriod;


    PeriodTypeDvcd(final String firstPeriod, final String secondPeriod) {

        this.firstPeriod = firstPeriod;

        this.secondPeriod = secondPeriod;

    }

}