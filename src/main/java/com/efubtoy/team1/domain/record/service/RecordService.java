package com.efubtoy.team1.domain.record.service;

import com.efubtoy.team1.domain.record.domain.Record;
import com.efubtoy.team1.domain.record.repository.RecordRepository;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    /* id 로 record 조회 */
    public Record findRecordByRecordId(Long recordId) {
        return recordRepository.findById(recordId)
                .orElseThrow(()->new CustomException(ErrorCode.RECORD_NOT_FOUND));
    }
}
