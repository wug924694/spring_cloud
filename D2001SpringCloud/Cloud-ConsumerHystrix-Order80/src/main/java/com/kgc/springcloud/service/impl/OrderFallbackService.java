package com.kgc.springcloud.service.impl;


import cn.hutool.core.util.IdUtil;
import com.kgc.springcloud.service.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;


//针对服务器做降级兜底
@Component
public class OrderFallbackService implements OrderHystrixService {

    @Override
    public String payMentHystrix_Ok(Integer id) {
        return "服务器payMentHystrix_Ok 繁忙";
    }

    @Override
    public String payMentHystrix_Timeout(Integer id) {
        return "服务器payMentHystrix_Timeout 繁忙";
    }

    //服务熔断配置
    @HystrixCommand(fallbackMethod ="paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "ID" + "调用成功，流水号："+ serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id不能为负数" + id;
    }
}
