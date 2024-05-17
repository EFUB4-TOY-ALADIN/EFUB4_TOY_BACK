package com.efubtoy.team1.domain.goods.dto;

import com.efubtoy.team1.domain.goods.domian.Goods;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsDto {
    private long goods_id;
    private String goods_name;
    private int price;
    private int stock;
    private String location;
    private String ref_image;

    public static GoodsDto of(Goods e){
        return GoodsDto.builder()
                .goods_id(e.getGoodsId())
                .goods_name(e.getGoodsName())
                .price(e.getPrice())
                .stock(e.getStock())
                .location(e.getLocation())
                .ref_image(e.getRefImage())
                .build();
    }
}
