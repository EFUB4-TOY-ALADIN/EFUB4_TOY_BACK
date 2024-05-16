package com.efubtoy.team1.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //Auth
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다."),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 만료되었습니다."),
    NON_LOGIN(HttpStatus.UNAUTHORIZED, "로그인 후 이용 가능합니다."),
    INVALID_ACCESS(HttpStatus.BAD_REQUEST,"올바르지 않은 접근입니다. 헤더를 확인해주세요."),

    //Account
    INVALID_ACCOUNT(HttpStatus.BAD_REQUEST, "접근 권한이 없는 회원입니다."),

    //Record
    RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 음반입니다."),

    //UsedBook
    USED_BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 중고도서입니다."),

    //UsedRecord
    USED_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 중고음반입니다."),

    //Goods
    GOODS_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 굿즈입니다."),

    //Cart
    ALREADY_ADDED(HttpStatus.BAD_REQUEST, "이미 장바구니에 존재하는 상품입니다."),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 장바구니입니다."),

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "올바르지 않은 요청입니다."),

    //review
    IMAGE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드 에러" );


    private final HttpStatus status;
    private final String message;
}