package com.efubtoy.team1.domain.record.domain;

import com.efubtoy.team1.domain.cart.domain.Cart;
import com.efubtoy.team1.global.customEnum.State;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Used_Record")
@Getter
@NoArgsConstructor
public class UsedRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "used_record_id")
    private Long usedRecordId;

    private int price;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id", updatable = false)
    private Record record;

    @OneToMany(mappedBy = "usedRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cartList = new ArrayList<>();
}
