package com.kgc.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.kgc.springcloud.MyBlockHander.MyBlockHandler;
import com.kgc.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/rate")
    @SentinelResource(value = "rate",blockHandler = "rateHandler")
    public CommonResult rate(){
        return new CommonResult(200,"访问成功");
    }


    public CommonResult rateHandler(BlockException exception){
        return new CommonResult(400,"服务器繁忙");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"访问成功");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrlHandler",blockHandlerClass = MyBlockHandler.class,blockHandler ="handlerException1" )
    public CommonResult byUrlHandler(BlockException exception){
        return new CommonResult(400,"服务器繁忙");
    }
}
