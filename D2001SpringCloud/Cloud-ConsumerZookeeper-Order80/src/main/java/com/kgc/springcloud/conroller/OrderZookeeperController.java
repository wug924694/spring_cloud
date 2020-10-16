package com.kgc.springcloud.conroller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderZookeeperController {
    @Resource
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://cloud-provider-service";

    @GetMapping("/consumer/payment/zk")
    public String PayMentInit(){
        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/zk", String.class);
        return result;
    }
}
