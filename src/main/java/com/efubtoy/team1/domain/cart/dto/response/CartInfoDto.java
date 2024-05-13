package com.efubtoy.team1.domain.cart.dto.response;


import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CartInfoDto {
    private List<CartItemDto> carts;

    public static CartInfoDto of(List<CartItemDto> cartItemDtoList){
        return CartInfoDto.builder()
                .carts(cartItemDtoList)
                .build();
    }
}
