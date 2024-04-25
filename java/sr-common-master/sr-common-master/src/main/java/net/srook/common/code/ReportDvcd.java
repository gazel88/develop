package net.srook.common.code;

import lombok.Getter;

@Getter
public enum ReportDvcd {
    ELEMENTARY("SE"), EDU("NM"), BUDDY("SB"), KAMPING("SN");

    private final String productCateCd;

    ReportDvcd(final String productCateCd) {
        this.productCateCd = productCateCd;
    }
}
