package net.srook.common.code;

import lombok.Getter;

@Getter
public enum AttendanceDvcd {
    START("등원"), END("하원");

    private final String value;

    AttendanceDvcd(final String value) {
        this.value = value;
    }
}
