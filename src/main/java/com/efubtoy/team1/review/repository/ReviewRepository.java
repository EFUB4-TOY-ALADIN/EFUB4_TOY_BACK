package com.efubtoy.team1.review.repository;

import com.efubtoy.team1.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
