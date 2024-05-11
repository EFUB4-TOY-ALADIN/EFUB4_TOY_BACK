package com.efubtoy.team1.cart.controller;


import com.efubtoy.team1.account.domain.Account;
import com.efubtoy.team1.account.service.AccountService;
import com.efubtoy.team1.auth.annotation.AuthUser;
import com.efubtoy.team1.cart.domain.Cart;
import com.efubtoy.team1.cart.dto.CartRequestDto;
import com.efubtoy.team1.cart.dto.CartResponseDto;
import com.efubtoy.team1.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final AccountService accountService;

    /* 장바구니에 상품 추가 */
    @PostMapping
    public ResponseEntity<CartResponseDto> addCart(@AuthUser Account account,
                                                   @RequestBody CartRequestDto requestDto){

        //Long accountId =  1L;
        //Account account = accountService.findAccountById(accountId);
        Cart cart = cartService.addCart(account, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartService.createDto(account,cart));
    }

}
