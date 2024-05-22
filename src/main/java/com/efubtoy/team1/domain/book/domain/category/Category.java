package com.efubtoy.team1.domain.book.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    CAT1(1L,"건강/취미"),
    CAT2(2L,"고전"),
    CAT3(3L,"과학"),
    CAT4(4L,"대학교재/전문서적"),
    CAT5(5L,"만화"),
    CAT6(6L,"달력/기타"),
    CAT7(7L,"사회과학"),
    CAT8(8L,"소설/시/희곡"),
    CAT9(9L,"수험서/자격증"),
    CAT10(10L,"어린이"),
    CAT11(11L,"에세이");


    private final Long categoryNum;
    private final String category;

    /* categoryNum으로 Category 문자열을 찾는 메서드입니다 */
    public static String getCategoryByNum(Long categoryNum){
        for(Category cat:Category.values()){
            if(cat.getCategoryNum().equals(categoryNum)){
                return cat.getCategory();
            }
        }
        throw new RuntimeException(categoryNum+"번의 카테고리가 존재하지 않습니다.");
    }
}
