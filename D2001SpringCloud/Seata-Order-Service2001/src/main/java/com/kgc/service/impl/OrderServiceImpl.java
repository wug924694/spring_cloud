package com.kgc.service.impl;


import com.kgc.dao.OrderDao;
import com.kgc.domain.Order;
import com.kgc.service.AccountService;
import com.kgc.service.OrderService;
import com.kgc.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class) //seata事务回滚
    public void create(Order order) {
        log.info("开始创建订单");
        orderDao.create(order);
        log.info("订单已生成，扣除库存");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("扣除库存成功");
        log.info("开始扣除金额");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("扣除金额成功");
        log.info("开始修改订单状态");
        orderDao.update(order.getUserId(),0);
        log.info("订单状态修改成功");
    }

    @Override
    public void update(Long userId, Integer status) {
        orderDao.update(userId,status);
    }
}
