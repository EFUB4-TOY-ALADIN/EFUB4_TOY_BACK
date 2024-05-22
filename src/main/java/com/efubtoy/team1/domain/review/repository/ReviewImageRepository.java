package com.efubtoy.team1.domain.review.repository;

import com.efubtoy.team1.domain.review.domain.Review;
import com.efubtoy.team1.domain.review.domain.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    //리뷰로 이미지 찾기
    public String findImageByReview(Review review);
}
