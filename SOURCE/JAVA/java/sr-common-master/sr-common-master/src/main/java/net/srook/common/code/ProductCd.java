package net.srook.common.code;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum ProductCd {
    SE("SE0000", "초등 푸르넷", "https://mme.epurunet.co.kr/data/ksp/pudding/SE.png"),
    SI("SI0000", "초등 푸르넷", "https://mme.epurunet.co.kr/data/ksp/pudding/SE.png"),
    NM("NM0000", "중등 에듀", "https://mme.epurunet.co.kr/data/ksp/pudding/NM.png"),
    NM1("NM0001", "중등 에듀 추가", "https://mme.epurunet.co.kr/data/ksp/pudding/NM.png"),
    SA("SA0030", "푸르넷 수학", "https://mme.epurunet.co.kr/data/ksp/pudding/SA.png"),
    SB0("SB0000", "잉글리시버디", "https://mme.epurunet.co.kr/data/ksp/pudding/SB0.png"),
    SB11("SB1100", "잉글리시버디", "https://mme.epurunet.co.kr/data/ksp/pudding/SB11.png"),
    SB1A("SB1A00", "잉글리시버디", "https://mme.epurunet.co.kr/data/ksp/pudding/SB1A.png"),
    SC("SC0000", "푸르넷 한자", "https://mme.epurunet.co.kr/data/ksp/pudding/SC.png"),
    SF("SF0000", "푸르넷 수떼기", "https://mme.epurunet.co.kr/data/ksp/pudding/SFSH.png"),
    SG("SG0000", "오! 역사논술", "https://mme.epurunet.co.kr/data/ksp/pudding/SG.png"),
    SH("SH0000", "푸르넷 한글떼기", "https://mme.epurunet.co.kr/data/ksp/pudding/SFSH.png"),
    SL("SL0000", "리틀 푸르넷", "https://mme.epurunet.co.kr/data/ksp/pudding/SL.jpg"),
    SR("SR0000", "독서 논술", "https://mme.epurunet.co.kr/data/ksp/pudding/SR.jpg"),
    SN("SN0000", "캠핑",     ""),
    Y156("Y156", "수리수리 수학나라", "https://mme.epurunet.co.kr/data/ksp/pudding/Y156.png"),
    T116C( "116C", "헬로리딩 세계명작동화", "https://mme.epurunet.co.kr/data/ksp/pudding/116C.jpg"),
    T116J( "116J", "드림북스 세계명작", "https://mme.epurunet.co.kr/data/ksp/pudding/116J.png"),
    T118C( "118C", "헬로리딩 빛을남긴사람들", "https://mme.epurunet.co.kr/data/ksp/pudding/118C.jpg"),
    T133C( "133C", "80일간의 세계일주", "https://mme.epurunet.co.kr/data/ksp/pudding/133C.png"),
    T134U( "134U", "과학지식만화 Aha", "https://mme.epurunet.co.kr/data/ksp/pudding/134U.png"),
    T136E( "136E", "오! 한국사", "https://mme.epurunet.co.kr/data/ksp/pudding/136E.png"),
    T136KS( "136KS", "오! 한국사", "https://mme.epurunet.co.kr/data/ksp/pudding/136KS.png"),
    T136P( "136P", "오! 한국사 인물 만화", "https://mme.epurunet.co.kr/data/ksp/pudding/136P.png"),
    T138H( "138H", "오! 세계사", "https://mme.epurunet.co.kr/data/ksp/pudding/138H.png"),
    T226( "226", "드림북스 꿈을 이룬 사람들", "https://mme.epurunet.co.kr/data/ksp/pudding/226.jpg"),
    T258C( "258C", "삼국지", "https://mme.epurunet.co.kr/data/ksp/pudding/258C.jpg"),
    T260( "260", "드림북스 피플스토리( 1차 )", "https://mme.epurunet.co.kr/data/ksp/pudding/260.jpg"),
    T261( "261", "칸타타 러닝", "https://mme.epurunet.co.kr/data/ksp/pudding/261.png"),
    T262( "262", "디즈니", "https://mme.epurunet.co.kr/data/ksp/pudding/262.jpg"),
    T263( "263", "한국문학", "https://mme.epurunet.co.kr/data/ksp/pudding/263.jpg"),
    T264( "264", "스토리 플러스", "https://mme.epurunet.co.kr/data/ksp/pudding/264.png"),
    T271KS( "271KS", "한국 고전", "https://mme.epurunet.co.kr/data/ksp/pudding/271KS.png"),
    NONE("None", "", "");

    private final String productCd;
    private final String productName;
    private final String imagePath;

    ProductCd(final String productCd, final String productName, final String imagePath) {
        this.productCd = productCd;
        this.productName = productName;
        this.imagePath = imagePath;
    }

    public static ProductCd findProductCd(final String productCd) {
        return Arrays.stream(values())
                .filter(cd -> cd.productCd.startsWith(productCd))
                .findFirst()
                .orElse(NONE);
    }
}
