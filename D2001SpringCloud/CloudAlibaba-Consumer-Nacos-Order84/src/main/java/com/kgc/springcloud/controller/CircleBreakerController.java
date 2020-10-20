package com.kgc.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.kgc.springcloud.entities.CommonResult;
import com.kgc.springcloud.entities.PayMent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallBack")
    //@SentinelResource(value = "fallBack",fallback = "handlerFallBack") //fallback可以管java异常
    //@SentinelResource(value = "fallBack",blockHandler = "blockHandler") //blockHandler可以管Sentinel管理的异常
    @SentinelResource(
            value = "fallBack",
            blockHandler = "blockHandler",
            fallback = "handlerFallBack",//blockHandler优先级高于fallback
            exceptionsToIgnore = {IllegalArgumentException.class}//忽略指定异常 不兜底
    )
    public CommonResult fallBack(@PathVariable("id") Long id){
        CommonResult result = restTemplate.getForObject(SERVICE_URL + "/payMentSql/" + id, CommonResult.class,id);
        if(id == 4){
            throw new IllegalArgumentException("非法参数");
        }else if(result.getData() == null){
            throw  new NullPointerException("空指针异常");
        }
        return result;
    }

    public CommonResult handlerFallBack(@PathVariable("id") Long id,Throwable e){
        PayMent payMent = new PayMent(id,null);
        return new CommonResult(400,"兜底异常fallback"+e.getMessage(),payMent);
    }

    public CommonResult blockHandler(@PathVariable("id") Long id,BlockException e){
        PayMent payMent = new PayMent(id,null);
        return new CommonResult(400,"兜底异常blockHandler"+e.getClass().getConstructors(),payMent);
    }
}
