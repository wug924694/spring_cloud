package com.kgc.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalance {
    //定义原子变量表示访问次数
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current; //当前初始化次数
        int next; //当前访问次数
        do{
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current +1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("这是第"+next+"次访问");
        return next;
    }
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        // 索引 = 第几次访问 % 服务总数量
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
