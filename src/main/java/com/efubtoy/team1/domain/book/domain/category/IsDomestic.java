package com.efubtoy.team1.domain.book.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IsDomestic {
    DOMESTIC(1,"국내 도서",true),
    NOTDOMESTIC(2,"해외 도서", false);
    private final int domesticNum;
    private final String domestic;
    private final boolean isDomestic;

    public static String getDomesticByIsDomestic(boolean isDomestic){
        for (IsDomestic dom : IsDomestic.values()){
            if (dom.isDomestic==isDomestic){
                return dom.getDomestic();
            }
        }
        throw new RuntimeException("도서분류가 존재하지 않습니다.");
    }

    public static boolean getIsDomesticByDomesticNum(int domesticNum){
        for(IsDomestic dom : IsDomestic.values()){
            if (dom.getDomesticNum()==domesticNum){
                return dom.isDomestic;
            }
        }
        throw new RuntimeException("도서분류가 존재하지 않습니다.");
    }
}
