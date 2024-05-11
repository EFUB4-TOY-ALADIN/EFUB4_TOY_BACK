package com.efubtoy.team1.domain.book.repository;

import com.efubtoy.team1.domain.book.domain.UsedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedBookRepository extends JpaRepository<UsedBook, Long> {
}
