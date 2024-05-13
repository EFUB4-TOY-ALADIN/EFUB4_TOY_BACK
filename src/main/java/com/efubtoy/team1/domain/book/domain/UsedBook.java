package com.efubtoy.team1.domain.book.domain;

import com.efubtoy.team1.domain.cart.domain.Cart;
import com.efubtoy.team1.global.customEnum.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Used_Book")
@Getter
@NoArgsConstructor
@NotNull
public class UsedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "used_book_id")
    private Long usedBookId;

    private int price;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", updatable = false)
    private Book book;

    @OneToMany(mappedBy = "usedBook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cartList = new ArrayList<>();

}
