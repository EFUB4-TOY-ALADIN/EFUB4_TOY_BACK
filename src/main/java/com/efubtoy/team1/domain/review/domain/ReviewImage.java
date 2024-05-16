package com.efubtoy.team1.domain.review.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Review_Image")
@Getter
@NoArgsConstructor
@NotNull
public class ReviewImage{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Review review;

    @Builder
    public ReviewImage( String imageUrl, Review review) {
        this.imageUrl = imageUrl;
        this.review = review;
    }
}
