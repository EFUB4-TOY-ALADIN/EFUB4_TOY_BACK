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
    private Long goods_id;
    private String goods_name;
    private Long price;
    private Long stock;
    private String location;
    private String info;
    private String ref_image;

    public static GoodsDto of(Goods e){
        return GoodsDto.builder()
                .goods_id(e.getGoodsId())
                .goods_name(e.getGoodsName())
                .price(e.getPrice())
                .stock(e.getStock())
                .location(e.getLocation())
                .info(e.getInfo())
                .ref_image(e.getRefImage())
                .build();
    }
}
