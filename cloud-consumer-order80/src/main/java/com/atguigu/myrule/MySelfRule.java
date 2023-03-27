package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhonghua
 * @date: 2/20/2023 2:52 PM
 * @description:
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        // define as random rule LB
        return new RandomRule();
    }
}
