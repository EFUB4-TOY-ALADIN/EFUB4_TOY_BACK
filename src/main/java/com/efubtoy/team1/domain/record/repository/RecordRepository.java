package com.efubtoy.team1.domain.record.repository;

import com.efubtoy.team1.domain.book.dto.BookListResponseDto;
import com.efubtoy.team1.domain.record.domain.Record;
import com.efubtoy.team1.domain.record.dto.response.RecordListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record,Long> {
    List<Record> findByCategoryAndTopic(Long category, Long topic);

    @Query("SELECT r from Record r where r.title LIKE %:searchWord%")
    Optional<List<Record>> findByTitle(@Param("searchWord") String searchWord);
}
