package com.efubtoy.team1.domain.goods.contoller;


import com.efubtoy.team1.domain.goods.dto.GoodsResponseDto;
import com.efubtoy.team1.domain.goods.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
