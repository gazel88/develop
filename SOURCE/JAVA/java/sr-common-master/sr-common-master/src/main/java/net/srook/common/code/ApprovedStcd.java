package net.srook.common.code;

import lombok.Getter;

@Getter
public enum ApprovedStcd {
    APPROVED("승인"),
    REFUSE("거절"),
    REQUEST("요청");

    private final String comment;

    ApprovedStcd(final String comment) {
        this.comment = comment;
    }
}
