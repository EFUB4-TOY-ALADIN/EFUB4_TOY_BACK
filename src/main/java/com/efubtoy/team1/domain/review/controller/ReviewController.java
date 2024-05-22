package com.efubtoy.team1.domain.review.controller;

import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.auth.annotation.AuthUser;
import com.efubtoy.team1.domain.review.domain.Review;
import com.efubtoy.team1.domain.review.dto.ReviewImageResponseDto;
import com.efubtoy.team1.domain.review.dto.ReviewRequestDto;
import com.efubtoy.team1.domain.review.dto.ReviewResponseDto;
import com.efubtoy.team1.domain.review.service.ReviewService;
import com.efubtoy.team1.global.AmazonS3.FileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final FileService fileUploadService;

    /* 리뷰 작성 */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ReviewResponseDto uploadReview(@AuthUser Account account ,
                             @RequestPart(value= "ReviewImage" , required = false) List<MultipartFile> multipartFiles ,
                             ReviewRequestDto reviewRequestDto){
        List<String> urlList = fileUploadService.fileUpload(multipartFiles);
        System.out.println("Image 경로 : " + urlList);
        Review savedReview = reviewService.saveReview(account , reviewRequestDto , urlList ); // 이미지 경로들 받아서 리뷰 생성
        return ReviewResponseDto.from(savedReview);
    }

    /* 리뷰 조회 */
    @GetMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<ReviewImageResponseDto> getAllReview(){
        List<ReviewImageResponseDto> list = new ArrayList<>();
        List<Review> reviews = reviewService.findAllReview();

        for(Review review : reviews){
            ReviewImageResponseDto dto = ReviewImageResponseDto.from(review);
            list.add(dto);
        }

        return list;
    }

    /* 리뷰 삭제 */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String DeleteReview(@PathVariable(name = "id") Long id){
        reviewService.deleteReview(id);
        return "리뷰가 삭제되었습니다.";
    }

}
