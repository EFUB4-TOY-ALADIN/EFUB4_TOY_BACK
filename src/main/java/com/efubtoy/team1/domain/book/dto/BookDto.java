package com.efubtoy.team1.domain.book.dto;

import com.efubtoy.team1.domain.book.domain.Book;
import com.efubtoy.team1.domain.book.domain.category.BookCategory;
import com.efubtoy.team1.domain.book.domain.category.IsDomestic;
import com.efubtoy.team1.domain.book.domain.category.BookTopic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private Long regularPrice;
    private String location;
    private Long stock;
    private String domestic;
    private String category;
    private String topic;
    private String refImage;

    public static BookDto of(Book book){
        return BookDto.builder()
                .id(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .regularPrice(book.getRegularPrice())
                .location(book.getLocation())
                .stock(book.getStock())
                .domestic(IsDomestic.getDomesticByIsDomestic(book.getIsDomestic()))
                .category(BookCategory.getCategoryByNum(book.getCategory()))
                .topic(BookTopic.getTopicByTopicNum(book.getTopic()))
                .refImage(book.getRefImage())
                .build();

    }
}
