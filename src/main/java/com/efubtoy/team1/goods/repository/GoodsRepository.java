package com.efubtoy.team1.goods.repository;

import com.efubtoy.team1.goods.domian.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods,Long> {
}
