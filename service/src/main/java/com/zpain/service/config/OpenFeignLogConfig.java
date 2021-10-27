package com.zpain.service.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangjun
 * @date 2021/10/19  17:07
 */
@Configuration
public class OpenFeignLogConfig {

    @Bean
    Logger.Level feignLoggerLeave(){
        return Logger.Level.FULL;
    }
}
