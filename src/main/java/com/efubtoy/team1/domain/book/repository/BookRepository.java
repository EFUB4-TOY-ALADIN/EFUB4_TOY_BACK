package com.efubtoy.team1.domain.book.repository;

import com.efubtoy.team1.domain.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
