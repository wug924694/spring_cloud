package com.kgc.springcloud.service;

import com.kgc.springcloud.entities.CommonResult;
import com.kgc.springcloud.entities.PayMent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient("CLOUD-PROVIDER-SERVICE")
public interface OrderFeignService {


    @PostMapping(value = "/payment/create")
    public CommonResult creat(@RequestBody PayMent payMent);

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPayMentById(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    public String PayMentFeignTimeOut();
}
