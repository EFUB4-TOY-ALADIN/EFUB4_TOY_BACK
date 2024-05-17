package com.efubtoy.team1.domain.goods.controller;

import com.efubtoy.team1.domain.goods.dto.GoodsDto;
import com.efubtoy.team1.domain.goods.dto.GoodsListResponseDto;
import com.efubtoy.team1.domain.goods.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<GoodsListResponseDto> findGoodsList(){
        List<GoodsDto> goodsDtoList = goodsService.findAllGoods();
        return ResponseEntity.ok().body(GoodsListResponseDto.of(goodsDtoList));
    }
}
