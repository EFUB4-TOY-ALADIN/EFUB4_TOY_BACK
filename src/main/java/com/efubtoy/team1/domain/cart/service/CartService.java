package com.efubtoy.team1.domain.cart.service;

import com.efubtoy.team1.domain.cart.dto.response.CartItemDto;
import com.efubtoy.team1.domain.cart.dto.request.CartRequestDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final UsedBookService usedBookService;
    private final UsedRecordService usedRecordService;
    private final GoodsService goodsService;


    /* 장바구니에 상품 추가 */
    public ResponseEntity<CartItemDto> addCart(Account account, CartRequestDto requestDto) {
        if(isAlreadyAdded(account,requestDto)) throw new CustomException(ErrorCode.ALREADY_ADDED);
        Cart cart = createCartByItemType(account, requestDto);
        cartRepository.save(cart);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createCartItemDto(cart));
    }

    /* 장바구니에 상품 삭제 */
    public ResponseEntity deleteCart(Account account, Long cartId) {
        Cart cart = findCartById(cartId);
        if(!cart.getAccount().equals(account)) throw new CustomException(ErrorCode.INVALID_ACCOUNT);
        cartRepository.delete(cart);
        return ResponseEntity.status(HttpStatus.OK)
                .body("장바구니가 삭제되었습니다!");
    }

    /* 장바구니 id로 장바구니 조회 */
    public Cart findCartById(Long cartId){
        return cartRepository.findById(cartId)
                .orElseThrow(()->new CustomException(ErrorCode.CART_NOT_FOUND));
    }

    /* 회원의 장바구니 목록 조회 */
    public ResponseEntity<List<CartItemDto>> findCartListByAccount(Account account) {
        List<Cart> cartList = cartRepository.findAllByAccount(account);

        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        for(Cart cart : cartList) { cartItemDtoList.add(createCartItemDto(cart));}
        return ResponseEntity.status(HttpStatus.OK)
                .body(cartItemDtoList);
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

    /* 응답 dto 생성 */
    public CartItemDto createCartItemDto(Cart cart){
        if(cart.getItemType().equals(ItemType.BOOK)) return CartItemDto.cartUsedBookDto(cart);
        else if(cart.getItemType().equals(ItemType.RECORD)) return CartItemDto.cartUsedRecordDto(cart);
        else if(cart.getItemType().equals(ItemType.GOODS)) return CartItemDto.cartGoodsDto(cart);
        else throw new CustomException(ErrorCode.INVALID_REQUEST);
    }
}
