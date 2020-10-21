package com.kgc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "seata-storage-service")
public interface StorageService {

    @GetMapping("/storage/decrease")
    void decrease(@RequestParam("productId")Long productId, @RequestParam("count") Integer count);
}
