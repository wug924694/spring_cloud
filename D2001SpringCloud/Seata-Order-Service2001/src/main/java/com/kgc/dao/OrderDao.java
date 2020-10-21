package com.kgc.dao;

import com.kgc.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    /**
     * 创建订单
     */
    void create(Order order);

    /**
     * 修改订单
     * @param userId
     * @param status 0代表未支付  1代表已支付
     */
    void update(@Param("userId")Long userId,@Param("status")Integer status);
}

