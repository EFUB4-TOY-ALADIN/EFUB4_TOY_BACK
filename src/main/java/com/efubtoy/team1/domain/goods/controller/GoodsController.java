package com.efubtoy.team1.domain.goods.controller;


import com.efubtoy.team1.domain.goods.dto.GoodsDto;
import com.efubtoy.team1.domain.goods.dto.GoodsListResponseDto;
import com.efubtoy.team1.domain.goods.dto.GoodsResponseDto;
import com.efubtoy.team1.domain.goods.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;

    /* 굿즈 상세 조회 */
    @GetMapping("/{goodsId}")
    public ResponseEntity<GoodsResponseDto> getGoods(@PathVariable("goodsId") Long goodsId){
        return goodsService.getGoods(goodsId);
    }

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<GoodsListResponseDto> findGoodsList(){
        List<GoodsDto> goodsDtoList = goodsService.findAllGoods();
        return ResponseEntity.ok().body(GoodsListResponseDto.of(goodsDtoList));
    }

    @GetMapping("/test")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> test(){
        return ResponseEntity.ok().body("test");
    }
}
