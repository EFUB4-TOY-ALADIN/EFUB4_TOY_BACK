package com.efubtoy.team1.book.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Book")
@Getter
@NoArgsConstructor
@NotNull
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    private String title;

    private String author;

    @Column(length = 100)
    private String publisher;

    @Column(name = "regular_price")
    private int regularPrice;

    @Column(length = 20)
    private String location;

    @Column(name = "is_domestic")
    private boolean isDomestic;

    @Column(columnDefinition = "TINYINT")
    private int category;

    @Column(columnDefinition = "TINYINT")
    private int topic;

    @Column(name = "ref_image")
    private String refImage;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsedBook> usedBookList = new ArrayList<>();

}
