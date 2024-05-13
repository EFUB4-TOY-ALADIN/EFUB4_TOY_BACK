package com.efubtoy.team1.domain.account.controller;

import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.account.service.AccountService;
import com.efubtoy.team1.domain.auth.annotation.AuthUser;
import com.efubtoy.team1.domain.cart.dto.response.CartItemDto;
import com.efubtoy.team1.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts/carts")
public class AccountCartController {

    private final AccountService accountService;
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getAccountCartList( @AuthUser Account account){
        return cartService.findCartListByAccount(account);
    }
}
