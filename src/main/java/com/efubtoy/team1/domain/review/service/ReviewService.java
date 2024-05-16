package com.efubtoy.team1.domain.review.service;


import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.review.domain.Review;
import com.efubtoy.team1.domain.review.domain.ReviewImage;
import com.efubtoy.team1.domain.review.dto.ReviewRequestDto;
import com.efubtoy.team1.domain.review.repository.ReviewImageRepository;
import com.efubtoy.team1.domain.review.repository.ReviewRepository;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;


    /* 리뷰 테이블에 사진 url들과 리뷰 내용 저장 */
    public Review saveReview(Account account ,ReviewRequestDto reviewRequestDto, List<String> urlList ) {
        Review review = reviewRequestDto.toEntity(account);
        Review savedReview = reviewRepository.save(review);
        saveReviewImage(savedReview.getReviewId() , urlList); // 반환받은 id로 리뷰 이미지 테이블 저장
        return savedReview;
    }

    /*리뷰 이미지와 리뷰 id를 리뷰 이미지 테이블에 저장*/
    public void saveReviewImage(Long reviewId , List<String> urlList){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));

        for (String imageUrl : urlList) {
            ReviewImage reviewImage = new ReviewImage(imageUrl , review);
            reviewImageRepository.save(reviewImage);
        }
    }



}
