package net.srook.common.code;

import lombok.Getter;

@Getter
public enum GradeCd {
    M1("중등 1", 1),
    M2("중등 2", 2),
    M3("중등 3", 3),
    MA("중등 전체", 9),
    P0("초등 예비", 0),
    P1("초등 1", 1),
    P2("초등 2", 2),
    P3("초등 3", 3),
    P4("초등 4", 4),
    P5("초등 5", 5),
    P6("초등 6", 6),
    PA("초등 전체", 9);

    private final String name;
    private final int grade;

    GradeCd(final String name, final int grade) {
        this.name = name;
        this.grade = grade;
    }
}
