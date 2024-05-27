package com.efubtoy.team1.domain.record.domain.category;


import com.efubtoy.team1.domain.book.domain.category.BookTopic;
import com.efubtoy.team1.domain.record.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;


// 이 topic은 category1 (가요)의 카테고리입니다
@Getter
@AllArgsConstructor
public enum RecordTopic {
    TOP1(1L, "뉴에이지"),
    TOP2(2L,"댄스뮤직"),
    TOP3(3L,"발라드"),
    TOP4(4L, "성인가요");
    private final Long topicNum;
    private final String topic;

    public static String getTopicByTopicNum(Long topicNum){
        for(RecordTopic topic : RecordTopic.values()){
            if (topic.getTopicNum().equals(topicNum)){
                return topic.getTopic();
            }
        }
        throw new RuntimeException(topicNum+"번의 토픽이 존재하지 않습니다.");
    }

}
