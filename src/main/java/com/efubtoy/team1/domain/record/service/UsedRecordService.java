package com.efubtoy.team1.domain.record.service;

import com.efubtoy.team1.domain.record.domain.Record;
import com.efubtoy.team1.domain.record.domain.UsedRecord;
import com.efubtoy.team1.domain.record.dto.response.RecordInfoResponseDto;
import com.efubtoy.team1.domain.record.repository.UsedRecordRepository;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsedRecordService {
    private final UsedRecordRepository usedRecordRepository;

    private final RecordService recordService;

    /* 음반 상세 목록 조회 */
    public ResponseEntity<RecordInfoResponseDto> findUsedRecordList(Long recordId) {
        Record record = recordService.findRecordByRecordId(recordId);
        List<UsedRecord> usedRecordList = findUsedRecordListByRecord(record);

        return ResponseEntity.status(HttpStatus.OK)
                .body(createRecordInfoResponseDto(record,usedRecordList));
    }

    /* record 의 모든 usedRecord 목록 조회 */
    public List<UsedRecord> findUsedRecordListByRecord(Record record){
        return usedRecordRepository.findAllByRecord(record);
    }

    /* id 로 userRecord 조회 */
    public UsedRecord findUsedRecordById(Long usedRecordId){
        return usedRecordRepository.findById(usedRecordId)
                .orElseThrow(()->new CustomException(ErrorCode.USED_RECORD_NOT_FOUND));
    }

    /* 상세 음반 조회 응답 dto 생성 */
    public RecordInfoResponseDto createRecordInfoResponseDto(Record record, List<UsedRecord> usedRecordList){
        Long lowestPrice = (long) usedRecordRepository.findAllByRecordOrderByPrice(record).get(0).getPrice();
        Long stock = (long) usedRecordList.size();

        return RecordInfoResponseDto.of(record,lowestPrice,stock,usedRecordList);
    }
}
