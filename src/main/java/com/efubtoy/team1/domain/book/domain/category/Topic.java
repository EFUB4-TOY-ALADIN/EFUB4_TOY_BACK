package com.efubtoy.team1.domain.book.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 이 topic은 category1 (건강/취미)의 카테고리입니다
@AllArgsConstructor
@Getter
public enum Topic {
    TOP1(1, "건강운동"),
    TOP2(2,"걷기정보"),
    TOP3(3,"공예"),
    TOP4(4, "글씨쓰기");
    private final int topicNum;
    private final String topic;

    public static String getTopicByTopicNum(int topicNum){
        for(Topic topic : Topic.values()){
            if (topic.getTopicNum()==topicNum){
                return topic.getTopic();
            }
        }
        throw new RuntimeException(topicNum+"번의 토픽이 존재하지 않습니다.");
    }
    
}
