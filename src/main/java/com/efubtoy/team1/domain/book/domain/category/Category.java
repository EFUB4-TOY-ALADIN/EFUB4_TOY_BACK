package com.efubtoy.team1.domain.book.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    CAT1(1,"건강/취미"),
    CAT2(2,"고전"),
    CAT3(3,"과학"),
    CAT4(4,"대학교재/전문서적"),
    CAT5(5,"만화"),
    CAT6(6,"달력/기타"),
    CAT7(7,"사회과학"),
    CAT8(8,"소설/시/희곡"),
    CAT9(9,"수험서/자격증"),
    CAT10(10,"어린이"),
    CAT11(11,"에세이");


    private final int categoryNum;
    private final String category;

    /* categoryNum으로 Category 문자열을 찾는 메서드입니다 */
    public static String getCategoryByNum(int categoryNum){
        for(Category cat:Category.values()){
            if(cat.getCategoryNum()==categoryNum){
                return cat.getCategory();
            }
        }
        throw new RuntimeException(categoryNum+"번의 카테고리가 존재하지 않습니다.");
    }
}
