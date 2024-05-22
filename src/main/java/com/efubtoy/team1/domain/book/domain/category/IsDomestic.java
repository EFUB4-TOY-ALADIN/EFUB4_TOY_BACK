package com.efubtoy.team1.domain.book.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IsDomestic {
    DOMESTIC(1L,"국내 도서",true),
    NOTDOMESTIC(2L,"해외 도서", false);
    private final Long domesticNum;
    private final String domestic;
    private final Boolean isDomestic;

    public static String getDomesticByIsDomestic(Boolean isDomestic){
        for (IsDomestic dom : IsDomestic.values()){
            if (dom.isDomestic.equals(isDomestic)){
                return dom.getDomestic();
            }
        }
        throw new RuntimeException("도서분류가 존재하지 않습니다.");
    }

    public static Boolean getIsDomesticByDomesticNum(Long domesticNum){
        for(IsDomestic dom : IsDomestic.values()){
            if (dom.getDomesticNum().equals(domesticNum)){
                return dom.isDomestic;
            }
        }
        throw new RuntimeException("도서분류가 존재하지 않습니다.");
    }
}
