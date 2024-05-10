package com.efubtoy.team1.cart.repository;

import com.efubtoy.team1.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
