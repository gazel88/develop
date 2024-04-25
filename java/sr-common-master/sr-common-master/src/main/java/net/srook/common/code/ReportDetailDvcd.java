package net.srook.common.code;

import static net.srook.common.code.ReportDvcd.*;

import lombok.Getter;

@Getter
public enum ReportDetailDvcd {
    MONTHLY(ELEMENTARY), TERM(ELEMENTARY), MOCK_EXAM(ELEMENTARY),
    EDU(ReportDvcd.EDU),
    BEGINNER(BUDDY), PRIME(BUDDY), EXPERIENCE(BUDDY),
    DAILYLEARN(KAMPING), DAILYREADBOOK(KAMPING), MONTHLYLEARN(KAMPING), MONTHLYREADBOOK(KAMPING) ;


    private final ReportDvcd parentDvcd;

    ReportDetailDvcd(final ReportDvcd parentDvcd) {
        this.parentDvcd = parentDvcd;
    }
}
