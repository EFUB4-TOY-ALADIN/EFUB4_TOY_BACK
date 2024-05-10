package com.efubtoy.team1.review.domain;

import com.efubtoy.team1.account.domain.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "create_at", updatable = false)
    private Timestamp createAt=new Timestamp(System.currentTimeMillis());


    private Long grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", updatable = false)
    private Account account;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImage> reviewImageList=new ArrayList<>();
}
