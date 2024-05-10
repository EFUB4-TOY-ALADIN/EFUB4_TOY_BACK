package com.efubtoy.team1.Goods.repository;

import com.efubtoy.team1.Goods.domian.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods,Long> {
}
