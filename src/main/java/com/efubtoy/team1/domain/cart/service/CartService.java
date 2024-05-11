package com.efubtoy.team1.domain.cart.service;

import com.efubtoy.team1.domain.cart.dto.CartResponseDto;
import com.efubtoy.team1.domain.cart.dto.CartRequestDto;
import com.efubtoy.team1.domain.goods.domian.Goods;
import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.book.domain.UsedBook;
import com.efubtoy.team1.domain.book.service.UsedBookService;
import com.efubtoy.team1.domain.cart.domain.Cart;
import com.efubtoy.team1.domain.cart.repository.CartRepository;
import com.efubtoy.team1.global.customEnum.ItemType;
import com.efubtoy.team1.domain.goods.service.GoodsService;
import com.efubtoy.team1.domain.record.domain.UsedRecord;
import com.efubtoy.team1.domain.record.service.UsedRecordService;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final UsedBookService usedBookService;
    private final UsedRecordService usedRecordService;
    private final GoodsService goodsService;


    /* 장바구니에 상품 추가 */
    public Cart addCart(Account account, CartRequestDto requestDto) {

        /* 장바구니에 이미 존재하는 상품인 경우, 예외 발생 */
        if(isAlreadyAdded(account,requestDto)) throw new CustomException(ErrorCode.ALREADY_ADDED);

        Cart cart = createCartByItemType(account, requestDto);
        cartRepository.save(cart);
        return cart;
    }

    public Cart createCartByItemType(Account account,CartRequestDto requestDto){
        Cart cart;
        switch (requestDto.getItemType()) {
            case "book" -> {
                cart = addUsedBookCart(account,requestDto);
                break;
            }
            case "record" -> {
                cart = addUsedRecordCart(account,requestDto);
                break;
            }
            case "goods" -> {
                cart = addGoodsCart(account,requestDto);
                break;
            }
            default -> cart = Cart.builder().build();
        }
        return cart;
    }


    /* 장바구니에 중고 도서 담기 */
    public Cart addUsedBookCart(Account account, CartRequestDto requestDto){
        UsedBook usedBook = usedBookService.findUsedBookById(requestDto.getItemId());
        if(cartRepository.existsByAccountAndUsedBook(account,usedBook)) throw new CustomException(ErrorCode.USED_BOOK_NOT_FOUND);

        return Cart.builder()
                .account(account)
                .itemType(ItemType.BOOK)
                .usedBook(usedBook)
                .build();
    }

    /* 장바구니에 중고 음반 담기 */
    public Cart addUsedRecordCart(Account account, CartRequestDto requestDto){
        UsedRecord usedRecord = usedRecordService.findUsedRecordById(requestDto.getItemId());
        if(cartRepository.existsByAccountAndUsedRecord(account,usedRecord)) throw new IllegalArgumentException("이미 담겨져 있습니다.");

        return Cart.builder()
                .account(account)
                .itemType(ItemType.RECORD)
                .usedRecord(usedRecord)
                .build();
    }

    /* 장바구니에 굿즈 담기 */
    public Cart addGoodsCart(Account account, CartRequestDto requestDto){
        Goods goods = goodsService.findGoodsById(requestDto.getItemId());

        return Cart.builder()
                .account(account)
                .itemType(ItemType.GOODS)
                .goods(goods)
                .build();
    }


    public CartResponseDto createDto(Account account, Cart cart){
        Long itemId = null;
        if(cart.getItemType().equals(ItemType.BOOK)){
            itemId = cart.getUsedBook().getUsedBookId();
        }
        else if(cart.getItemType().equals(ItemType.RECORD)){
            itemId = cart.getUsedRecord().getUsedRecordId();
        }
        else if(cart.getItemType().equals(ItemType.GOODS)){
            itemId = cart.getGoods().getGoodsId();
        }

        return CartResponseDto.builder()
                .nickname(account.getNickname())
                .itemType(cart.getItemType().getType())
                .itemId(itemId)
                .build();
    }

    /* 이미 장바구니에 있는 상품인지 확인 */
    public Boolean isAlreadyAdded(Account account, CartRequestDto requestDto){
        switch (requestDto.getItemType()) {
            case "book" -> {
                UsedBook usedBook = usedBookService.findUsedBookById(requestDto.getItemId());
                if(cartRepository.existsByAccountAndUsedBook(account,usedBook)) return Boolean.TRUE;
                break;
            }
            case "record" -> {
                UsedRecord usedRecord = usedRecordService.findUsedRecordById(requestDto.getItemId());
                if(cartRepository.existsByAccountAndUsedRecord(account,usedRecord)) return Boolean.TRUE;
                break;
            }
            case "goods" -> {
                Goods goods = goodsService.findGoodsById(requestDto.getItemId());
                if(cartRepository.existsByAccountAndGoods(account,goods)) return Boolean.TRUE;
                break;
            }

        }
        return Boolean.FALSE;
    }

}
