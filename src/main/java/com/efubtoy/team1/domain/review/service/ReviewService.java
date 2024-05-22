package com.efubtoy.team1.domain.review.service;


import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.review.domain.Review;
import com.efubtoy.team1.domain.review.domain.ReviewImage;
import com.efubtoy.team1.domain.review.dto.ReviewRequestDto;
import com.efubtoy.team1.domain.review.repository.ReviewImageRepository;
import com.efubtoy.team1.domain.review.repository.ReviewRepository;
import com.efubtoy.team1.global.AmazonS3.FileDto;
import com.efubtoy.team1.global.AmazonS3.FileService;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final FileService fileService;


    /* 리뷰 테이블에 사진 url들과 리뷰 내용 저장 */
    public Review saveReview(Account account ,ReviewRequestDto reviewRequestDto, List<FileDto> urlList ) {
        Review review = reviewRequestDto.toEntity(account);
        Review savedReview = reviewRepository.save(review);
        saveReviewImage(savedReview.getReviewId() , urlList); // 반환받은 id로 리뷰 이미지 테이블 저장
        return savedReview;
    }

    /*리뷰 이미지와 리뷰 id를 리뷰 이미지 테이블에 저장*/
    public void saveReviewImage(Long reviewId , List<FileDto> fileDtos){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));

        for (FileDto fileDto : fileDtos) {
            ReviewImage reviewImage = new ReviewImage(fileDto.getImgUrl() , fileDto.getFileName(), review);
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
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("해당 id를 가진 Post를 찾을 수 없습니다. id="+id));
        ReviewImage reviewImage = reviewImageRepository.findImageByReview(review);
        fileService.deleteImage(reviewImage.getImageUrl(), reviewImage.getFileName());
        reviewRepository.delete(review);
    }
}
