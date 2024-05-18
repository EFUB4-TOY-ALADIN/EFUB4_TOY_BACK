package com.efubtoy.team1.domain.book.repository;

import com.efubtoy.team1.domain.book.domain.Book;
import com.efubtoy.team1.domain.book.domain.UsedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsedBookRepository extends JpaRepository<UsedBook, Long> {
    List<UsedBook> findAllByBook(Book book);
    List<UsedBook> findAllByBookOrderByPrice(Book book);

}
