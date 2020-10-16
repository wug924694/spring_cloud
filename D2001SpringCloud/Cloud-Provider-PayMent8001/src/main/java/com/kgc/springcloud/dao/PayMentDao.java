package com.kgc.springcloud.dao;

import com.kgc.springcloud.entities.PayMent;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PayMentDao {

    /**
     * 插入一个支付信息
     * @param payMent
     * @return
     */
    public int create(PayMent payMent);


    /**
     * 获取支付信息
     * @param id
     * @return
     */
    public PayMent getPayMentById(Long id);


}
