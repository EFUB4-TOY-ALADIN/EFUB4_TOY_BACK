package com.efubtoy.team1.domain.review.controller;

import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.auth.annotation.AuthUser;
import com.efubtoy.team1.domain.review.domain.Review;
import com.efubtoy.team1.domain.review.dto.ReviewRequestDto;
import com.efubtoy.team1.domain.review.dto.ReviewResponseDto;
import com.efubtoy.team1.domain.review.service.ReviewService;
import com.efubtoy.team1.global.AmazonS3.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final FileUploadService fileUploadService;

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


}
