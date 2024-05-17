package com.efubtoy.team1.domain.goods.service;

import com.efubtoy.team1.domain.goods.domian.Goods;
import com.efubtoy.team1.domain.goods.dto.GoodsDto;
import com.efubtoy.team1.domain.goods.repository.GoodsRepository;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;

    public Goods findGoodsById(Long goodsId){
        return goodsRepository.findById(goodsId)
                .orElseThrow(()->new CustomException(ErrorCode.GOODS_NOT_FOUND));
    }

    public List<GoodsDto> findAllGoods(){
        List<Goods> goodsList = goodsRepository.findAll();
        List<GoodsDto> goodsDtoList = new ArrayList<>();
        for (Goods goods:goodsList){
            goodsDtoList.add(GoodsDto.of(goods));
        }
        return goodsDtoList;
    }
}
