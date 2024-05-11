package com.efubtoy.team1.goods.service;

import com.efubtoy.team1.goods.domian.Goods;
import com.efubtoy.team1.goods.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;

    public Goods findGoodsById(Long goodsId){
        return goodsRepository.findById(goodsId)
                .orElseThrow(()->new IllegalArgumentException("Unexpected goods"));
    }
}
