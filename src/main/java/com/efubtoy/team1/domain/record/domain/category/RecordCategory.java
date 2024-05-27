package com.efubtoy.team1.domain.record.domain.category;

import com.efubtoy.team1.domain.book.domain.category.BookCategory;
import com.efubtoy.team1.domain.record.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecordCategory {
    CAT1(1L,"가요"),
    CAT2(2L,"국악"),
    CAT3(3L,"월드뮤직"),
    CAT4(4L,"재즈"),
    CAT5(5L,"종교/명상/기타"),
    CAT6(6L,"클래식"),
    CAT7(7L,"팝"),
    CAT8(8L,"해외구매"),
    CAT9(9L,"J-pop"),
    CAT10(10L,"LP"),
    CAT11(11L,"O.S.T");


    private final Long categoryNum;
    private final String category;

    /* categoryNum으로 Category 문자열을 찾는 메서드입니다 */
    public static String getCategoryByNum(Long categoryNum){
        for(RecordCategory cat: RecordCategory.values()){
            if(cat.getCategoryNum().equals(categoryNum)){
                return cat.getCategory();
            }
        }
        throw new RuntimeException(categoryNum+"번의 카테고리가 존재하지 않습니다.");
    }
}
