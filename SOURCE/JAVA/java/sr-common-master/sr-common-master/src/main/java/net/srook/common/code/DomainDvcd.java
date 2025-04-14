package net.srook.common.code;

import lombok.Getter;

@Getter
public enum DomainDvcd {
    PARENTS("학부모", "parents"),
    TEACHERS("선생님", "teachers"),
    ONLINE_CONTRACT("전자계약", "online-contracts"),
    TEACHER_REFERENCE("교사자료실", "teacher-references"),
    SAMPLES("예제", "samples")
    ;

    private final String name;
    private final String path;

    DomainDvcd(final String name, final String path) {
        this.name = name;
        this.path = path;
    }

    public boolean equalsByName(final String name) {
        return this.name().equalsIgnoreCase(name);
    }
}
