package com.efubtoy.team1.domain.cart.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDto {

    private String itemType;
    private Long itemId;
}
