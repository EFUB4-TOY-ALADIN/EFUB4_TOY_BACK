package com.efubtoy.team1.domain.record.dto.response;

import com.efubtoy.team1.domain.record.domain.category.RecordCategory;
import com.efubtoy.team1.domain.record.domain.category.RecordTopic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordListResponseDto {

    private String category;

    private String topic;

    private List<RecordResponseDto> recordList = new ArrayList<>();

    public static RecordListResponseDto of(Long category , Long topic , List<RecordResponseDto> recordList){

        return new RecordListResponseDto(
                RecordCategory.getCategoryByNum(category),
                RecordTopic.getTopicByTopicNum(topic),
                recordList
        );

    }
}
