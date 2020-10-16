package com.kgc.springcloud.controller;

import com.kgc.springcloud.entities.CommonResult;
import com.kgc.springcloud.entities.PayMent;
import com.kgc.springcloud.service.OrderFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private OrderFeignService orderFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult getPayMentById(@PathVariable("id") Long id){
       return orderFeignService.getPayMentById(id);
    }

    @PostMapping(value = "/consumer/payment/create")
    public CommonResult creat(@RequestBody PayMent payMent){
       return orderFeignService.creat(payMent);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String PayMentFeignTimeOut(){
        return orderFeignService.PayMentFeignTimeOut();
    }
}
