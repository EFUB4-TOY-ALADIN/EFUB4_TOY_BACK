package com.efubtoy.team1.domain.review.dto;

import com.efubtoy.team1.domain.review.domain.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewResponseDto {

    private Long review_id;
    private Long grade;
    private String content;
    private LocalDateTime created_at;

    public static ReviewResponseDto from (Review review){
        return new ReviewResponseDto(
                review.getReviewId(),
                review.getGrade(),
                review.getContent(),
                review.getCreated_at()
        );

    }
}
