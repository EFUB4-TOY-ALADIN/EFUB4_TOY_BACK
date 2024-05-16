package com.efubtoy.team1.domain.book.dto;

import com.efubtoy.team1.domain.book.domain.Book;
import com.efubtoy.team1.domain.book.domain.category.Category;
import com.efubtoy.team1.domain.book.domain.category.IsDomestic;
import com.efubtoy.team1.domain.book.domain.category.Topic;
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
    private int regularPrice;
    private String location;
    private int stock;
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
                .domestic(IsDomestic.getDomesticByIsDomestic(book.isDomestic()))
                .category(Category.getCategoryByNum(book.getCategory()))
                .topic(Topic.getTopicByTopicNum(book.getTopic()))
                .refImage(book.getRefImage())
                .build();

    }
}
