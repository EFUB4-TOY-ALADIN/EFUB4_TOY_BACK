package com.efubtoy.team1.domain.cart.dto.response;

import com.efubtoy.team1.domain.book.domain.UsedBook;
import com.efubtoy.team1.domain.cart.domain.Cart;
import com.efubtoy.team1.domain.goods.domian.Goods;
import com.efubtoy.team1.domain.record.domain.UsedRecord;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItemDto {

    private Long cartId;
    private String itemType;
    private Long itemId;
    private String title;
    private String producer;
    private String publisher;
    private Long price;
    private String location;
    private String itemImgUrl;


    public static CartItemDto cartUsedBookDto(Cart cart){
        return CartItemDto.builder()
                .cartId(cart.getCartId())
                .itemType("book")
                .itemId(cart.getUsedBook().getUsedBookId())
                .title(cart.getUsedBook().getBook().getTitle())
                .producer(cart.getUsedBook().getBook().getAuthor())
                .publisher(cart.getUsedBook().getBook().getPublisher())
                .price((long) cart.getUsedBook().getPrice())
                .location(cart.getUsedBook().getBook().getLocation())
                .itemImgUrl(cart.getUsedBook().getBook().getRefImage())
                .build();
    }

    public static CartItemDto cartUsedRecordDto(Cart cart){
        return CartItemDto.builder()
                .cartId(cart.getCartId())
                .itemType("record")
                .itemId(cart.getUsedRecord().getUsedRecordId())
                .title(cart.getUsedRecord().getRecord().getTitle())
                .producer(cart.getUsedRecord().getRecord().getSinger())
                .publisher(cart.getUsedRecord().getRecord().getPublisher())
                .price((long)cart.getUsedRecord().getPrice())
                .location(cart.getUsedRecord().getRecord().getLocation())
                .itemImgUrl(cart.getUsedRecord().getRecord().getRefImage())
                .build();
    }

    public static CartItemDto cartGoodsDto(Cart cart){
        return CartItemDto.builder()
                .cartId(cart.getCartId())
                .itemType("goods")
                .itemId(cart.getGoods().getGoodsId())
                .title(cart.getGoods().getGoodsName())
                .price((long)cart.getGoods().getPrice())
                .location(cart.getGoods().getLocation())
                .itemImgUrl(cart.getGoods().getRefImage())
                .build();
    }

}
