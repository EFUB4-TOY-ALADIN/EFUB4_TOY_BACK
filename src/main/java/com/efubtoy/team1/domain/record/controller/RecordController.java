package com.efubtoy.team1.domain.record.controller;

import com.efubtoy.team1.domain.record.domain.Record;
import com.efubtoy.team1.domain.record.dto.response.RecordInfoResponseDto;
import com.efubtoy.team1.domain.record.dto.response.RecordResponseDto;
import com.efubtoy.team1.domain.record.service.RecordService;
import com.efubtoy.team1.domain.record.service.UsedRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
