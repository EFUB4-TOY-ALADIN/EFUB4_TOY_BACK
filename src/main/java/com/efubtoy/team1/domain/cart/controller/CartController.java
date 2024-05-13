package com.efubtoy.team1.domain.cart.controller;


import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.account.service.AccountService;
import com.efubtoy.team1.domain.auth.annotation.AuthUser;
import com.efubtoy.team1.domain.cart.domain.Cart;
import com.efubtoy.team1.domain.cart.dto.request.CartRequestDto;
import com.efubtoy.team1.domain.cart.dto.response.CartItemDto;
import com.efubtoy.team1.domain.cart.dto.response.CartResponseDto;
import com.efubtoy.team1.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    /* 장바구니에 상품 추가 */
    @PostMapping
    public ResponseEntity<CartItemDto> addCart(@AuthUser Account account, @RequestBody CartRequestDto requestDto){
        return cartService.addCart(account,requestDto);

    }

    /* 장바구니에 상품 삭제 */
    @DeleteMapping("/{cartId}")
    public ResponseEntity deleteCart(@AuthUser Account account, @PathVariable("cartId") Long cartId){
        return cartService.deleteCart(account, cartId);

    }
}
