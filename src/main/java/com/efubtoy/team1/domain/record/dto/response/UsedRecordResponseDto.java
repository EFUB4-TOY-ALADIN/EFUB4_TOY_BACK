package com.efubtoy.team1.domain.record.dto.response;

import com.efubtoy.team1.domain.record.domain.UsedRecord;
import com.efubtoy.team1.global.customEnum.State;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UsedRecordResponseDto {
    private Long usedRecordId;
    private Long price;
    private State state;

    public static UsedRecordResponseDto of(UsedRecord usedRecord){
        return UsedRecordResponseDto.builder()
                .usedRecordId(usedRecord.getUsedRecordId())
                .price(usedRecord.getPrice())
                .state(usedRecord.getState())
                .build();
    }
}
