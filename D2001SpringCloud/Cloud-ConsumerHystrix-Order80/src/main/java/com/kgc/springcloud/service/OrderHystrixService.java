package com.kgc.springcloud.service;


import com.kgc.springcloud.service.impl.OrderFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-SERVICE",fallback = OrderFallbackService.class)
public interface OrderHystrixService {

    @GetMapping("/payment/hystrix_ok/{id}")
    public String payMentHystrix_Ok(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix_Timeout/{id}")
    public String payMentHystrix_Timeout(@PathVariable("id") Integer id);



}
