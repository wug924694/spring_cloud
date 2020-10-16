package com.kgc.springcloud.controller;


import com.kgc.springcloud.service.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "hystrix_Global_FallBack")
public class OrderHystrixController {
    @Resource
    private OrderHystrixService orderHystrixService;

    @GetMapping("/consumer/payment/hystrix_ok/{id}")
    public String payMentHystrix_Ok(@PathVariable("id") Integer id){
        return orderHystrixService.payMentHystrix_Ok(id);
    }

//    //配置兜底
//    @HystrixCommand(fallbackMethod = "hystrix_TimeoutFallBack",commandProperties = {
//          @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
//    @HystrixCommand//配置全局兜底
    @GetMapping("/consumer/payment/hystrix_Timeout/{id}")
    public String payMentHystrix_Timeout(@PathVariable("id") Integer id){
        return orderHystrixService.payMentHystrix_Timeout(id);
    }

    //兜底方法
    public String hystrix_TimeoutFallBack(Integer id){
        return "消费者你好 服务器走丢中";
    }

    //全局兜底
    public String hystrix_Global_FallBack(Integer id){
        return "服务器繁忙";
    }
}
