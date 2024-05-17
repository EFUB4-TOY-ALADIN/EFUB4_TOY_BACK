package com.efubtoy.team1.domain.goods.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
public class GoodsListResponseDto {
    private List<GoodsDto> goodsList;

    public static GoodsListResponseDto of(List<GoodsDto> goodsDtoList){
        return GoodsListResponseDto.builder()
                .goodsList(goodsDtoList)
                .build();
    }
}
