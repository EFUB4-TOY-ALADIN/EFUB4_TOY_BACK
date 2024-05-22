package com.efubtoy.team1.domain.book.dto;

import com.efubtoy.team1.domain.book.domain.Book;
import com.efubtoy.team1.domain.book.domain.UsedBook;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BookInfoResponseDto {
    private BookResponseDto book;
    private List<UsedBookResponseDto> usedBookList;

    public static BookInfoResponseDto of(Book book, Long lowestPrice, Long stock, List<UsedBook> usedBookList){
        return BookInfoResponseDto.builder()
                .book(BookResponseDto.of(book,lowestPrice,stock))
                .usedBookList(usedBookList.stream().map(UsedBookResponseDto::of).collect(Collectors.toList()))
                .build();
    }
}
