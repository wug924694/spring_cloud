package com.kgc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id; //订单id
    private Long userId; //用户id
    private Long productId; //商品id
    private Integer count; //数量
    private BigDecimal money; //金额
    private Integer status; //0表示创建中 1表示已完结

}
