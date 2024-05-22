package com.efubtoy.team1.domain.book.controller;

import com.efubtoy.team1.domain.book.domain.Book;
import com.efubtoy.team1.domain.book.dto.BookInfoResponseDto;
import com.efubtoy.team1.domain.book.dto.BookListResponseDto;
import com.efubtoy.team1.domain.book.service.BookService;
import com.efubtoy.team1.domain.book.service.UsedBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final UsedBookService usedBookService;

    @GetMapping("/{is_domestic}/{category}/{topic}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<BookListResponseDto> findBookListByTopic(@PathVariable("is_domestic")Long isDomestic,
                                              @PathVariable("category")Long category,
                                              @PathVariable("topic")Long topic){
        List<Book> bookList = bookService.findBookByCategories(isDomestic, category, topic);
        BookListResponseDto dto = BookListResponseDto.of(isDomestic, category, topic, bookList);

        return ResponseEntity.ok().body(dto);
    }


    @GetMapping("/{bookId}")
    public ResponseEntity<BookInfoResponseDto> getUsedBookList(@PathVariable("bookId") Long bookId){
        return usedBookService.findUsedBookList(bookId);
    }


}
