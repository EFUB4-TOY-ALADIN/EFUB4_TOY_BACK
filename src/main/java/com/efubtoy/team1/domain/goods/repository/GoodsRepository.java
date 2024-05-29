package com.efubtoy.team1.domain.goods.repository;

import com.efubtoy.team1.domain.goods.domian.Goods;
import com.efubtoy.team1.domain.goods.dto.GoodsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods,Long> {

    @Query("SELECT g from Goods g where g.goodsName LIKE %:searchWord%")
    Optional<List<GoodsDto>> findByTitle(@Param("searchWord") String searchWord);
}
