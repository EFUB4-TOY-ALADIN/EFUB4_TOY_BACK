package com.efubtoy.team1.record.domain;


import com.efubtoy.team1.book.domain.UsedBook;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Record")
@Getter
@NoArgsConstructor
@NotNull
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId;

    @Column(length = 50)
    private String title;

    @Column(length = 50)
    private String singer;

    @Column(length = 50)
    private String publisher;

    @Column(name = "regular_price")
    private int regularPrice;

    @Column(length = 20)
    private String location;

    @Column(columnDefinition = "TINYINT")
    private int category;

    @Column(columnDefinition = "TINYINT")
    private int topic;

    @Column(name = "ref_image")
    private String refImage;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsedRecord> usedRecordList = new ArrayList<>();

}
