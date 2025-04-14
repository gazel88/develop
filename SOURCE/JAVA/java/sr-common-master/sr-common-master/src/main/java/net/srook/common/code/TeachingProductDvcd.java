package net.srook.common.code;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum TeachingProductDvcd {
    T116C("116C", "헬로리딩 세계명작동화", 360000, YNDvcd.N),
    T116J("116J", "세계명작", 400000, YNDvcd.N),
    T118C("118C", "헬로리딩 빛을남긴사람들",   360000, YNDvcd.N),
    T290("290",   "실록대하소설 조선왕조 500년", 360000, YNDvcd.N),
    T133C("133C", "80일간의 세계일주", 432000, YNDvcd.N),
    T134UA("134UA",   "과학지식만화 A-ha! A세트", 340000, YNDvcd.N),
    T134UB("134UB",   "과학지식만화 A-ha! B세트", 340000, YNDvcd.N),
    T136L("136L", "GO! 한국사 체험학습", 60000, YNDvcd.N),
    T136P("136P", "오! 한국사 인물만화", 240000, YNDvcd.N),
    T136KS("136KS", "오! 한국사", 320000, YNDvcd.N),
    T138H("138H", "오! 세계사", 380000, YNDvcd.N),
    T144("144",   "철학이야기", 300000, YNDvcd.N),
    T145("145", "푸르넷 위즈덤", 360000, YNDvcd.N),
    T146("146",   "개념원리 365", 80000, YNDvcd.N),
    T226("226",   "꿈을 이룬 사람들", 360000, YNDvcd.N),
    T258C("258C", "삼국지", 360000, YNDvcd.N),
    T262("262D", "디즈니 세트", 330000, YNDvcd.N),
    T262D("262D", "디즈니(40권)", 220000, YNDvcd.Y),
    T262E("262E", "디즈니(마블10권)", 55000, YNDvcd.Y),
    T262F("262F", "디즈니(스타워즈10권)", 55000, YNDvcd.Y),
    T263("263",   "한국문학", 360000, YNDvcd.N),
    T264("264",   "스토리 플러스", 384000, YNDvcd.N),
    T260("260", "피플스토리 1차",   240000, YNDvcd.N),
    T270("270",   "피플스토리 2차", 240000, YNDvcd.N),
    T271KS("271KS",   "한국고전", 320000, YNDvcd.N),
    T297("297", "고려 왕비열전", 240000, YNDvcd.N),
    T426("426",   "과학도감",   360000, YNDvcd.N),
    FE10001("FE10001", "힘이 붙는 수학 연산 초등 1학년 1학기",   9000, YNDvcd.N),
    FE10002("FE10002", "힘이 붙는 수학 연산 초등 1학년 2학기",   9000, YNDvcd.N),
    FE20001("FE20001", "힘이 붙는 수학 연산 초등 2학년 1학기",   9000, YNDvcd.N),
    FE20002("FE20002", "힘이 붙는 수학 연산 초등 2학년 2학기",   9000, YNDvcd.N),
    FE30001("FE30001", "힘이 붙는 수학 연산 초등 3학년 1학기",   9000, YNDvcd.N),
    FE30002("FE30002", "힘이 붙는 수학 연산 초등 3학년 2학기",   9000, YNDvcd.N),
    FE40001("FE40001", "힘이 붙는 수학 연산 초등 4학년 1학기",   9000, YNDvcd.N),
    FE40002("FE40002", "힘이 붙는 수학 연산 초등 4학년 2학기",   9000, YNDvcd.N),
    FE50001("FE50001", "힘이 붙는 수학 연산 초등 5학년 1학기",   9000, YNDvcd.N),
    FE50002("FE50002", "힘이 붙는 수학 연산 초등 5학년 2학기",   9000, YNDvcd.N),
    FE60001("FE60001", "힘이 붙는 수학 연산 초등 6학년 1학기",   9000, YNDvcd.N),
    FE60002("FE60002", "힘이 붙는 수학 연산 초등 6학년 2학기",   9000, YNDvcd.N),
    FM10001("FM10001", "힘이 붙는 수학 연산 중등 1학년 1학기", 13000, YNDvcd.N),
    FM10002("FM10002", "힘이 붙는 수학 연산 중등 1학년 2학기", 13000, YNDvcd.N),
    FM20001("FM20001", "힘이 붙는 수학 연산 중등 2학년 1학기", 13000, YNDvcd.N),
    FM20002("FM20002", "힘이 붙는 수학 연산 중등 2학년 2학기", 13000, YNDvcd.N),
    FM30001("FM30001", "힘이 붙는 수학 연산 중등 3학년 1학기", 13000, YNDvcd.N),
    FM30002("FM30002", "힘이 붙는 수학 연산 중등 3학년 2학기", 13000, YNDvcd.N),
    WBG0001("WBG0001", "웰컴투 베이직 그래머 1권", 12000, YNDvcd.N),
    WBG0002("WBG0002", "웰컴투 베이직 그래머 2권", 12000, YNDvcd.N),
    WBG0003("WBG0003", "웰컴투 베이직 그래머 3권", 12000, YNDvcd.N),
    NONE("", "", 0, YNDvcd.N);
    private final String productCd;
    private final String productName;
    private final int productPrice;
    private final YNDvcd packageProductYn;

    TeachingProductDvcd(String productCd, String productName, int productPrice, YNDvcd packageProductYn) {
        this.productCd = productCd;
        this.productName = productName;
        this.productPrice = productPrice;
        this.packageProductYn = packageProductYn;
    }

    public static TeachingProductDvcd findTeachingProductName(final String productCd) {
        return Arrays.stream(values())
                .filter(cd -> cd.productCd.equals(productCd))
                .findFirst()
                .orElse(NONE);
    }

    public static TeachingProductDvcd findPackageProductName(final String productCd, final YNDvcd packageProductYn) {
        return Arrays.stream(values())
                .filter(cd -> cd.productCd.equals(productCd))
                .filter(cd -> cd.packageProductYn.equals(packageProductYn))
                .findFirst()
                .orElse(NONE);
    }
}
