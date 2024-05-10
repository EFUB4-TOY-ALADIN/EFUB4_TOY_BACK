package com.efubtoy.team1.record.repository;

import com.efubtoy.team1.record.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record,Long> {
}
