package net.srook.common.code;

import lombok.Getter;

@Getter
public enum FileTycd {
    IMG("이미지", "images"),
    DOC("문서", "docs"),
    ZIP("압축파일", "zips");

    private final String name;
    private final String path;

    FileTycd(final String name, final String path) {
        this.name = name;
        this.path = path;
    }
}
