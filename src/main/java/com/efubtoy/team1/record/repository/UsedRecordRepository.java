package com.efubtoy.team1.record.repository;

import com.efubtoy.team1.record.domain.UsedRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedRecordRepository extends JpaRepository<UsedRecord, Long> {
}
