package com.efubtoy.team1.domain.goods.service;

import com.efubtoy.team1.domain.goods.domian.Goods;
import com.efubtoy.team1.domain.goods.dto.GoodsDto;
import com.efubtoy.team1.domain.goods.dto.GoodsResponseDto;
import com.efubtoy.team1.domain.goods.repository.GoodsRepository;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /* 굿즈 상세 조회 */
    public ResponseEntity<GoodsResponseDto> getGoods(Long goodsId) {
        Goods goods = findGoodsById(goodsId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(GoodsResponseDto.of(goods));
    }

    /* 굿즈 목록 조회 */
    public List<GoodsDto> findAllGoods(){
        List<Goods> goodsList = goodsRepository.findAll();
        List<GoodsDto> goodsDtoList = new ArrayList<>();
        for (Goods goods:goodsList){
            goodsDtoList.add(GoodsDto.of(goods));
        }
        return goodsDtoList;
    }
}
