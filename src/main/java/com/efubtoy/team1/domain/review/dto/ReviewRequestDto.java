package com.efubtoy.team1.domain.review.dto;

import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.review.domain.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewRequestDto {

    @NotBlank(message = "내용은 필수입니다.")
    private String content;
    @NotBlank(message = "평점은 필수입니다.")
    @Max(value = 6, message = "평점은 5 이하여야 합니다.")
    private Long grade;
    @NotBlank(message = "계정 id는 필수입니다.")
    private String accountId;


    public Review toEntity(Account account){
        return Review.builder()
                .account(account)
                .content(this.content)
                .grade(this.grade)
                .build();
    }
}
