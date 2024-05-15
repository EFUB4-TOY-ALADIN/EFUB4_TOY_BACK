package com.efubtoy.team1.domain.book.dto;

import com.efubtoy.team1.domain.book.domain.Book;
import com.efubtoy.team1.domain.book.domain.category.Category;
import com.efubtoy.team1.domain.book.domain.category.IsDomestic;
import com.efubtoy.team1.domain.book.domain.category.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookListResponseDto {
    private String domestic;
    private String category;
    private String topic;
    private List<BookDto> booklist=new ArrayList<>();

    public static BookListResponseDto of(int isDomestic, int category, int topic, List<Book> bookList){
        return BookListResponseDto.builder()
                .domestic(IsDomestic.getDomesticByIsDomestic(IsDomestic.getIsDomesticByDomesticNum(isDomestic)))
                .category(Category.getCategoryByNum(category))
                .topic(Topic.getTopicByTopicNum(topic))
                .booklist(bookList.stream().map(BookDto::of).collect(Collectors.toList()))
                .build();
    }

}
