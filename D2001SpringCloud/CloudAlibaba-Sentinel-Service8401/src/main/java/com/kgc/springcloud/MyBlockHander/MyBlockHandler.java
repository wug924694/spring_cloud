package com.kgc.springcloud.MyBlockHander;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.kgc.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class MyBlockHandler {

    public static CommonResult handlerException1(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName(),"自定义1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName(),"自定义2");
    }
}
