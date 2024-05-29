package com.efubtoy.team1.domain.goods.repository;

import com.efubtoy.team1.domain.goods.domian.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods,Long> {
}
