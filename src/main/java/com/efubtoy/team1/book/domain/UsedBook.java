package com.efubtoy.team1.book.domain;

import com.efubtoy.team1.global.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

}
