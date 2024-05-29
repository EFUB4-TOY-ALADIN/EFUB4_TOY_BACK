package com.efubtoy.team1.domain.book.repository;

import com.efubtoy.team1.domain.book.domain.Book;
import com.efubtoy.team1.domain.book.dto.BookDto;
import com.efubtoy.team1.domain.book.dto.BookListResponseDto;
import com.efubtoy.team1.domain.goods.dto.GoodsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByIsDomesticAndCategoryAndTopic(Boolean isDomestic, Long category, Long topic);


    @Query("SELECT b from Book b where b.title LIKE %:searchWord%")
    Optional<List<Book>> findByTitle(@Param("searchWord") String searchWord);
}
