package com.kgc.springcloud.controller;

import com.kgc.springcloud.entities.CommonResult;
import com.kgc.springcloud.entities.PayMent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PayMentController {

    @Value("${server.port}")
    private String serverPort;

    //模拟查询数据库
    public static HashMap<Long, PayMent> map = new HashMap<>();
    static{
        map.put(1L,new PayMent(1L,"1111111"));
        map.put(1L,new PayMent(1L,"2222222"));
        map.put(1L,new PayMent(1L,"3333333"));
    }

    @GetMapping("/payMentSql/{id}")
    public CommonResult payMentSql(@PathVariable("id") Long id){
        PayMent payMent = map.get(id);
        return new CommonResult(200,"serverPort"+serverPort,payMent);
    }
}
