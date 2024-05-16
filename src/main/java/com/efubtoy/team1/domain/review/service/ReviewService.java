package com.efubtoy.team1.domain.review.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.review.domain.Review;
import com.efubtoy.team1.domain.review.domain.ReviewImage;
import com.efubtoy.team1.domain.review.dto.ReviewRequestDto;
import com.efubtoy.team1.domain.review.repository.ReviewImageRepository;
import com.efubtoy.team1.domain.review.repository.ReviewRepository;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    /* 리뷰 이미지 S3에 업로드 */
    public List<String> fileUpload(List<MultipartFile> multipartFiles) {
        List<String> urlList = new ArrayList<>();
        if(multipartFiles != null && !multipartFiles.isEmpty()){
        for (MultipartFile file : multipartFiles) {
            String fileName = makeFileName(file); // 파일 이름 생성
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try (InputStream inputStream = file.getInputStream()) {
                amazonS3Client.putObject(new PutObjectRequest(bucket + "/review/image", fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
                urlList.add(amazonS3Client.getUrl(bucket + "/review/image", fileName).toString());
            } catch (IOException e) {
                throw new CustomException(ErrorCode.IMAGE_UPLOAD_ERROR, "image upload fail");
            }

        }

        }
        return urlList;
    }

    /* 파일명 생성 */
    private String makeFileName(MultipartFile multipartFile){
        String originalName = multipartFile.getOriginalFilename();
        final String ext = originalName.substring(originalName.lastIndexOf("."));
        final String fileName = UUID.randomUUID().toString() + ext;
        return fileName;
    }

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
                .orElseThrow(() -> new RuntimeException("Review not found"));

        for (String imageUrl : urlList) {
            ReviewImage reviewImage = new ReviewImage(imageUrl , review);
            reviewImageRepository.save(reviewImage);
        }
    }



}
