package com.efubtoy.team1.domain.record.repository;

import com.efubtoy.team1.domain.record.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Long> {
    List<Record> findByCategoryAndTopic(Long category, Long topic);
}
