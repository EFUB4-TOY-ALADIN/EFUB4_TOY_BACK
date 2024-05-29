package com.efubtoy.team1.domain.review.domain;

import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Review")
@Getter
@NoArgsConstructor
@NotNull
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(columnDefinition = "TEXT" , nullable = false, length = 10000)
    private String content;

    private Long grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", updatable = false)
    private Account account;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImage> reviewImageList=new ArrayList<>();

    @Builder
    public Review(String content, Timestamp createAt, Long grade, Account account, List<ReviewImage> reviewImageList) {
        this.content = content;
        this.grade = grade;
        this.account = account;
        this.reviewImageList = reviewImageList;
    }


}