package com.efubtoy.team1.domain.book.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private Long regularPrice;

    @Column(length = 20)
    private String location;

    @Transient
    private Long stock;

    @Transient
    private Long lowestPrice;

    @Column(name = "is_domestic")
    private Boolean isDomestic;

    @Column(columnDefinition = "TINYINT")
    private Long category;

    @Column(columnDefinition = "TINYINT")
    private Long topic;

    @Column(name = "ref_image")
    private String refImage;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsedBook> usedBookList = new ArrayList<>();

    public long getStock(){
        return usedBookList.size();
    }
    public long getLowestPrice(){
        long price=100000000000l;
        for (UsedBook book : usedBookList){
            if (price>book.getPrice()){
                price=book.getPrice();
            }
        }
        return price;
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    public void updateStock(){
        this.stock= (long) usedBookList.size();
    }


}
