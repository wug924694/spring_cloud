package com.kgc.service.impl;

import com.kgc.dao.StorageDao;
import com.kgc.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);
    @Resource
    private StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("开始减少库存");
        storageDao.decrease(productId,count);
        LOGGER.info("减少库存成功");
    }
}
