package com.efubtoy.team1.domain.book.service;

import com.efubtoy.team1.domain.book.domain.Book;
import com.efubtoy.team1.domain.book.domain.category.IsDomestic;
import com.efubtoy.team1.domain.book.dto.BookInfoResponseDto;
import com.efubtoy.team1.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book findBookById(Long bookId){
        return bookRepository.findById(bookId)
                .orElseThrow(()->new IllegalArgumentException("Unexpected book"));
    }

    public List<Book> findBookByCategories(Long isDomestic,
                                           Long category,
                                           Long topic){
        Boolean isDomesticB = IsDomestic.getIsDomesticByDomesticNum(isDomestic);
        return bookRepository.findByIsDomesticAndCategoryAndTopic(isDomesticB, category, topic);

    }
}
