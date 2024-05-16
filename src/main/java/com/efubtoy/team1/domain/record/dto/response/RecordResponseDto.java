package com.efubtoy.team1.domain.record.dto.response;

import com.efubtoy.team1.domain.record.domain.Record;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecordResponseDto {
    private Long recordId;
    private String title;
    private String singer;
    private String publisher;
    private Long price;
    private Long lowestPrice;
    private Long stock;
    private String location;
    private String refImage;

    public static RecordResponseDto of(Record record, Long lowestPrice,Long stock){
        return RecordResponseDto.builder()
                .recordId(record.getRecordId())
                .title(record.getTitle())
                .singer(record.getSinger())
                .publisher(record.getPublisher())
                .price(record.getRegularPrice())
                .lowestPrice(lowestPrice)
                .stock(stock)
                .location(record.getLocation())
                .refImage(record.getRefImage())
                .build();
    }
}
