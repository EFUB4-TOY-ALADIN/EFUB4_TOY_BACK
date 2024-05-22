package com.efubtoy.team1.domain.review.dto;

import com.efubtoy.team1.domain.review.domain.Review;
import com.efubtoy.team1.domain.review.domain.ReviewImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImageResponseDto {

    private Long review_id;
    private Long account_id;
    private Long grade;
    private String content;
    private LocalDateTime created_at;
    private List<ReviewImageDto> images;


    public static ReviewImageResponseDto from (Review review) {

        List<ReviewImageDto> imageDtos = review.getReviewImageList().stream()
                .map(ReviewImageDto::from)
                .collect(Collectors.toList());

        return new ReviewImageResponseDto(
                review.getReviewId(),
                review.getAccount().getAccountId(),
                review.getGrade(),
                review.getContent(),
                review.getCreated_at(),
                imageDtos
        );

    }
}
