package com.efubtoy.team1.cart.repository;

import com.efubtoy.team1.account.domain.Account;
import com.efubtoy.team1.book.domain.UsedBook;
import com.efubtoy.team1.cart.domain.Cart;
import com.efubtoy.team1.goods.domian.Goods;
import com.efubtoy.team1.record.domain.UsedRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Boolean existsByAccountAndUsedBook(Account account, UsedBook usedBook);
    Boolean existsByAccountAndUsedRecord(Account account, UsedRecord usedRecord);
    Boolean existsByAccountAndGoods(Account account, Goods goods);
}
