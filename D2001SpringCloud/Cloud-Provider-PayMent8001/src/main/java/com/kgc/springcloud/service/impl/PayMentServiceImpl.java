package com.kgc.springcloud.service.impl;

import com.kgc.springcloud.dao.PayMentDao;
import com.kgc.springcloud.entities.PayMent;
import com.kgc.springcloud.service.PayMentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PayMentServiceImpl implements PayMentService {
    @Resource
    private PayMentDao payMentDao;

    @Override
    public int create(PayMent payMent) {
        return payMentDao.create(payMent);
    }

    @Override
    public PayMent getPayMentById(Long id) {
        return payMentDao.getPayMentById(id);
    }
}
