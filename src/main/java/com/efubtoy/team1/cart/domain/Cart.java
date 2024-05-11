package com.efubtoy.team1.cart.domain;

import com.efubtoy.team1.goods.domian.Goods;
import com.efubtoy.team1.account.domain.Account;
import com.efubtoy.team1.book.domain.UsedBook;
import com.efubtoy.team1.global.ItemType;
import com.efubtoy.team1.record.domain.UsedRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cart")
@Getter
@NoArgsConstructor
@NotNull
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id", updatable = false)
    private Goods goods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "used_record_id", updatable = false)
    private UsedRecord usedRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "used_book_id", updatable = false)
    private UsedBook usedBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", updatable = false)
    private Account account;

    @Column(name = "item_type", length = 10)
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Builder
    public Cart(Account account, Goods goods, UsedBook usedBook, UsedRecord usedRecord, ItemType itemType){
        this.account = account;
        this.goods = goods;
        this.usedBook = usedBook;
        this.usedRecord = usedRecord;
        this.itemType = itemType;
    }
}
