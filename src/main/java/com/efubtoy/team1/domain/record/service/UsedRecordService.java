package com.efubtoy.team1.domain.record.service;

import com.efubtoy.team1.domain.record.domain.UsedRecord;
import com.efubtoy.team1.domain.record.repository.UsedRecordRepository;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsedRecordService {
    private final UsedRecordRepository usedRecordRepository;

    public UsedRecord findUsedRecordById(Long usedRecordId){
        return usedRecordRepository.findById(usedRecordId)
                .orElseThrow(()->new CustomException(ErrorCode.USED_RECORD_NOT_FOUND));
    }
}
