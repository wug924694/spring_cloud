package com.kgc.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PayMentHystrixService {


    public String PayMentHystrix_Ok(Integer id){
        return "当前线程池："+Thread.currentThread().getName()+"PayMentHystrix_Ok"+id;
    }

    //配置兜底
    @HystrixCommand(fallbackMethod = "payMentHystrix_TimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String PayMentHystrix_Timeout(Integer id) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return "当前线程池："+Thread.currentThread().getName()+"PayMentHystrix_Timeout"+id;
    }

    //兜底方法
    public String payMentHystrix_TimeoutHandler(Integer id){
        return "当前线程池："+Thread.currentThread().getName()+"payMentHystrix_TimeoutHandler"+id + "服务器开了个小差";
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
