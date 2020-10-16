package com.kgc.springcloud.service;

import com.kgc.springcloud.entities.PayMent;

public interface PayMentService {

    public int create(PayMent payMent);

    public PayMent getPayMentById(Long id);
}
