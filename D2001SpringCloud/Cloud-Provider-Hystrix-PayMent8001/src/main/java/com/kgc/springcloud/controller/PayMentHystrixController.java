package com.kgc.springcloud.controller;

import com.kgc.springcloud.service.PayMentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PayMentHystrixController {
    @Resource
    private PayMentHystrixService payMentHystrixService;

    @GetMapping("/payment/hystrix_ok/{id}")
    public String payMentHystrix_Ok(@PathVariable("id") Integer id){
       return payMentHystrixService.PayMentHystrix_Ok(id);
    }


    @GetMapping("/payment/hystrix_Timeout/{id}")
    public String payMentHystrix_Timeout(@PathVariable("id") Integer id) throws InterruptedException {
        return payMentHystrixService.PayMentHystrix_Timeout(id);
    }


    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        return payMentHystrixService.paymentCircuitBreaker(id);
    }

    @GetMapping("/payment/circuit/breaker/{id}")
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return payMentHystrixService.paymentCircuitBreaker_fallback(id);
    }
}
