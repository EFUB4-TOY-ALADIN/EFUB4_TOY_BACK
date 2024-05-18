package com.efubtoy.team1.domain.book.dto;

import com.efubtoy.team1.domain.book.domain.Book;
import com.efubtoy.team1.domain.record.dto.response.RecordResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookResponseDto {
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private Long price;
    private Long lowestPrice;
    private Long stock;
    private String location;
    private String refImage;

    public static BookResponseDto of(Book book, Long lowestPrice, Long stock){
        return BookResponseDto.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .price(book.getRegularPrice())
                .lowestPrice(lowestPrice)
                .stock(stock)
                .location(book.getLocation())
                .refImage(book.getRefImage())
                .build();
    }
}
