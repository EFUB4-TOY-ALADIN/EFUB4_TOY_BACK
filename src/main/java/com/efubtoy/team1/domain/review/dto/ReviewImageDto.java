package com.efubtoy.team1.domain.review.dto;

import com.efubtoy.team1.domain.review.domain.ReviewImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImageDto {

    private Long imageId;
    private String imageUrl;
    private String fileName;



    public static ReviewImageDto from(ReviewImage reviewImage) {
        return new ReviewImageDto(
                reviewImage.getImageId(),
                reviewImage.getImageUrl(),
                reviewImage.getFileName()
        );
    }




}
