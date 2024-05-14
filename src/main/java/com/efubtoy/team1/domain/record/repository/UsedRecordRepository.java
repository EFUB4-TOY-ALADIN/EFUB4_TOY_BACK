package com.efubtoy.team1.domain.record.repository;

import com.efubtoy.team1.domain.record.domain.Record;
import com.efubtoy.team1.domain.record.domain.UsedRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsedRecordRepository extends JpaRepository<UsedRecord, Long> {
    List<UsedRecord> findAllByRecord(Record record);
    List<UsedRecord> findAllByRecordOrderByPrice(Record record);
}
