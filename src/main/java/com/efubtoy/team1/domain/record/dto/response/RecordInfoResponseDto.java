package com.efubtoy.team1.domain.record.dto.response;

import com.efubtoy.team1.domain.record.domain.Record;
import com.efubtoy.team1.domain.record.domain.UsedRecord;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordInfoResponseDto {
    private RecordResponseDto record;
    private List<UsedRecordResponseDto> usedRecordList;

    public static RecordInfoResponseDto of(Record record,Long lowestPrice, Long stock, List<UsedRecord> usedRecordList){
        return RecordInfoResponseDto.builder()
                .record(RecordResponseDto.of(record,lowestPrice,stock))
                .usedRecordList(usedRecordList.stream().map(UsedRecordResponseDto::of).collect(Collectors.toList()))
                .build();
    }
}
