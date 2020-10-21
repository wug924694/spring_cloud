package com.kgc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.kgc.dao"})
public class MyBatisConfig {

}
