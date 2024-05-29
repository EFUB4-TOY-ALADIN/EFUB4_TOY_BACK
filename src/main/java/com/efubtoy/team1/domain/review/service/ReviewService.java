package com.efubtoy.team1.domain.review.service;


import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.review.domain.Review;
import com.efubtoy.team1.domain.review.domain.ReviewImage;
import com.efubtoy.team1.domain.review.dto.ReviewRequestDto;
import com.efubtoy.team1.domain.review.repository.ReviewImageRepository;
import com.efubtoy.team1.domain.review.repository.ReviewRepository;
import com.efubtoy.team1.global.AmazonS3.FileService;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
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
    private final FileService fileService;


    /* 리뷰 테이블에 사진 url들과 리뷰 내용 저장 */
    public Review saveReview(Account account , ReviewRequestDto reviewRequestDto, List<String> urlList ) {
        Review review = reviewRequestDto.toEntity(account);
        Review savedReview = reviewRepository.save(review);
        saveReviewImage(savedReview.getReviewId() , urlList); // 반환받은 id로 리뷰 이미지 테이블 저장
        return savedReview;
    }

    /*리뷰 이미지와 리뷰 id를 리뷰 이미지 테이블에 저장*/
    public void saveReviewImage(Long reviewId , List<String> urlLists){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));

        for (String imageUrl  : urlLists) {
            ReviewImage reviewImage = new ReviewImage(imageUrl, review);
            reviewImageRepository.save(reviewImage);
        }
    }

    /* 리뷰 전체 조회 */
    @Transactional(readOnly = true)
    public List<Review> findAllReview(){
        List<Review> reviews = reviewRepository.findAll();
        return reviews;
    }


    /* 리뷰 삭제 */
    public void deleteReview(Long reviewId , Account account) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()->new EntityNotFoundException("해당 id를 가진 리뷰를 찾을 수 없습니다. id="+ reviewId));
        if(!review.getAccount().getAccountId().equals(account.getAccountId())){ //리뷰의 작성자와 회원이 일치하지 않는 경우 , 예외
            throw  new CustomException(ErrorCode.INVALID_ACCESS);
        }
        //리뷰id에 해당하는 이미지들 배열로 받기
        List<ReviewImage> reviewImages = reviewImageRepository.findImageByReview(review);

        for (ReviewImage reviewImage : reviewImages) {
            fileService.deleteImage(reviewImage.getImageUrl());
        }
        reviewRepository.delete(review);
    }
}