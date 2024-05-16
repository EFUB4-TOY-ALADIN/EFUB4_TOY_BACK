package com.efubtoy.team1.domain.book.repository;

import com.efubtoy.team1.domain.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIsDomesticAndCategoryAndTopic(boolean isDomestic, int category, int topic);
}
