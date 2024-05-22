package com.efubtoy.team1.domain.book.dto;

import com.efubtoy.team1.domain.book.domain.UsedBook;
import com.efubtoy.team1.global.customEnum.State;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UsedBookResponseDto {
    private Long usedBookId;
    private Long price;
    private State state;

    public static UsedBookResponseDto of(UsedBook usedBook){
        return UsedBookResponseDto.builder()
                .usedBookId(usedBook.getUsedBookId())
                .price(usedBook.getPrice())
                .state(usedBook.getState())
                .build();
    }
}
