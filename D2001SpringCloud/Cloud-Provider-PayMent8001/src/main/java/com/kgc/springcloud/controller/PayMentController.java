package com.kgc.springcloud.controller;

import com.kgc.springcloud.entities.CommonResult;
import com.kgc.springcloud.entities.PayMent;
import com.kgc.springcloud.service.PayMentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PayMentController {
    @Resource
    private PayMentService payMentService;

    @Value("${server.port}")
    private String serverPort;


    @PostMapping(value = "/payment/create")
    public CommonResult creat(@RequestBody PayMent payMent){
        int result = payMentService.create(payMent);
        log.info("插入成功，结果为"+result);
        if(result >0){
            return new CommonResult(200,"插入成功"+result);
        }else {
            return new CommonResult(400,"插入失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPayMentById(@PathVariable("id") Long id){
        PayMent result = payMentService.getPayMentById(id);
        log.info("查询成功，结果为"+result);
        if(result != null){
            return new CommonResult(200,"查询成功"+result+serverPort);
        }else {
            return new CommonResult(400,"查询失败，id为"+id);
        }
    }

    @GetMapping("/payment/lb")
    public String getPayMent(){
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String PayMentFeignTimeOut() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String payMentZipKin(){
        return "8001 server";
    }
}
