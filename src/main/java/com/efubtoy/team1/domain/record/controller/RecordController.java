package com.efubtoy.team1.domain.record.controller;

import com.efubtoy.team1.domain.record.domain.Record;
import com.efubtoy.team1.domain.record.dto.response.RecordInfoResponseDto;
import com.efubtoy.team1.domain.record.dto.response.RecordListResponseDto;
import com.efubtoy.team1.domain.record.dto.response.RecordResponseDto;
import com.efubtoy.team1.domain.record.service.RecordService;
import com.efubtoy.team1.domain.record.service.UsedRecordService;
import jdk.jfr.consumer.RecordedClassLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;
    private final UsedRecordService usedRecordService;


    /* 상세 음반 조회 : 음반과 해당 음반의 중고 음반 목록 조회 */
    @GetMapping("/{recordId}")
    public ResponseEntity<RecordInfoResponseDto> getUsedRecordList(@PathVariable("recordId")Long recordId){
        return usedRecordService.findUsedRecordList(recordId);
    }

    /* 토픽별 음반 목록 조회*/
    @GetMapping("/{category}/{topic}")
    public ResponseEntity<RecordListResponseDto> getRecordList(@PathVariable("category")Long category ,
                                                                @PathVariable("topic")Long topic){
        List<Record> recordList1 = recordService.findRecordByTopic(category, topic);

        //음반의 최저가와 재고까지 조회한 dto
        List<RecordResponseDto> recordList2 = recordService.findRecordWithLowestAndStock(recordList1);

        RecordListResponseDto recordListResponseDto = RecordListResponseDto.of(category , topic , recordList2);
        return ResponseEntity.status(HttpStatus.OK).body(recordListResponseDto);
    }

}
