package com.efubtoy.team1.domain.goods.domian;

import com.efubtoy.team1.domain.cart.domain.Cart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Goods")
@Getter
@NoArgsConstructor
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "goods_name", length = 50)
    private String goodsName;

    private int price;

    private int stock;

    @Column(length = 20)
    private String location;

    @Column(name = "ref_image")
    private String refImage;

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cartList = new ArrayList<>();


}
