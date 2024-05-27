package com.efubtoy.team1.domain.record.service;

import com.efubtoy.team1.domain.record.domain.Record;
import com.efubtoy.team1.domain.record.domain.UsedRecord;
import com.efubtoy.team1.domain.record.dto.response.RecordResponseDto;
import com.efubtoy.team1.domain.record.repository.RecordRepository;
import com.efubtoy.team1.domain.record.repository.UsedRecordRepository;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final UsedRecordRepository usedRecordRepository;

    /* id 로 record 조회 */
    public Record findRecordByRecordId(Long recordId) {
        return recordRepository.findById(recordId)
                .orElseThrow(()->new CustomException(ErrorCode.RECORD_NOT_FOUND));
    }

    /* 토픽별 음반 조회 */
    public List<Record> findRecordByTopic(Long category , Long topic){
        return recordRepository.findByCategoryAndTopic(category , topic);
    }

    /* 음반의 최저가랑 재고 조회 */
    public List<RecordResponseDto> findRecordWithLowestAndStock(List<Record> recordList){
        return recordList.stream()
                .map(record -> {
                    List<UsedRecord> usedRecordList = usedRecordRepository.findAllByRecord(record);
                    Long lowestPrice = (long) usedRecordRepository.findAllByRecordOrderByPrice(record).get(0).getPrice();
                    Long stock = (long) usedRecordList.size();

                    return RecordResponseDto.of(record , lowestPrice , stock);
                })
                .collect(Collectors.toList());
    }
}
