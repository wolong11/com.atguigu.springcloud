package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhonghua
 * @date: 3/22/2023 9:58 PM
 * @description:
 */
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        // request and response's header info, content and meta
        return Logger.Level.FULL;
    }
}
