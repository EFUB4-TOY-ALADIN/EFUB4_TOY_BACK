package com.efubtoy.team1.domain.cart.controller;


import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.account.service.AccountService;
import com.efubtoy.team1.domain.auth.annotation.AuthUser;
import com.efubtoy.team1.domain.cart.domain.Cart;
import com.efubtoy.team1.domain.cart.dto.request.CartRequestDto;
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

    /* 장바구니에 상품 삭제 */
    @DeleteMapping("/{cartId}")
    public ResponseEntity deleteCart(@AuthUser Account account,
                                     @PathVariable("cartId") Long cartId){
        //Long accountId =  1L;
        //Account account = accountService.findAccountById(accountId);
        cartService.deleteCart(account, cartId);

        return ResponseEntity.status(HttpStatus.OK)
                .body("삭제되었습니다.");

    }

}
