package com.efubtoy.team1.domain.goods.dto;

import com.efubtoy.team1.domain.goods.domian.Goods;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GoodsResponseDto {
    private Long goodsId;
    private String goodsName;
    private Long price;
    private Long stock;
    private String location;
    private String refImage;


    public static GoodsResponseDto of(Goods goods){
        return GoodsResponseDto.builder()
                .goodsId(goods.getGoodsId())
                .goodsName(goods.getGoodsName())
                .price(goods.getPrice())
                .stock(goods.getStock())
                .location(goods.getLocation())
                .refImage(goods.getRefImage())
                .build();
    }
}
