package com.efubtoy.team1.book.service;

import com.efubtoy.team1.book.domain.Book;
import com.efubtoy.team1.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book findBookById(Long bookId){
        return bookRepository.findById(bookId)
                .orElseThrow(()->new IllegalArgumentException("Unexpected book"));
    }
}
