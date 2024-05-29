package com.efubtoy.team1.domain.goods.dto;

import lombok.*;

import java.util.List;

@Getter /* JSON PARSER에서 JSON을 생성할 때 getter를 사용해야 하므로 반드시 추가해줘야 합니다*/
@AllArgsConstructor
@Builder
public class GoodsListResponseDto {
    private List<GoodsDto> goodsList;

    public static GoodsListResponseDto of(List<GoodsDto> goodsDtoList){
        System.out.println("GoodsListResponseDto.of");
        return GoodsListResponseDto.builder()
                .goodsList(goodsDtoList)
                .build();
    }
}
