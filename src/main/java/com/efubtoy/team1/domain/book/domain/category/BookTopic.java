package com.efubtoy.team1.domain.book.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 이 topic은 category1 (건강/취미)의 카테고리입니다
@AllArgsConstructor
@Getter
public enum BookTopic {
    TOP1(1L, "건강운동"),
    TOP2(2L,"걷기정보"),
    TOP3(3L,"공예"),
    TOP4(4L, "글씨쓰기");
    private final Long topicNum;
    private final String topic;

    public static String getTopicByTopicNum(Long topicNum){
        for(BookTopic topic : BookTopic.values()){
            if (topic.getTopicNum().equals(topicNum)){
                return topic.getTopic();
            }
        }
        throw new RuntimeException(topicNum+"번의 토픽이 존재하지 않습니다.");
    }
    
}
