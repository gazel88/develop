package net.srook.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KSNext {
    private int size;
    private int page;

    public KSNext(final int size, final int page) {
        this.size = size;
        this.page = page;
    }
}
