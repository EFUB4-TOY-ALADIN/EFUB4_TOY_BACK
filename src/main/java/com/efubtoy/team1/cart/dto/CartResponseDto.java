package com.efubtoy.team1.cart.dto;

import com.efubtoy.team1.account.domain.Account;
import com.efubtoy.team1.cart.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartResponseDto {
    private String nickname;
    private String itemType;
    private Long itemId;


    public CartResponseDto(Account account, Cart cart){

    }


}
