package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhonghua
 * @date: 3/23/2023 11:37 AM
 * @description:
 */
@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "Thread pool: " + Thread.currentThread().getName() + "paymentInfo_OK, id:" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_timeout(Integer id) {
        int timeNumber = 3;
        try {
            //int a = 10 / 0;
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thread pool: " + Thread.currentThread().getName() + "paymentInfo_timeout, id:" + id;
    }

    public String paymentInfo_timeoutHandler(Integer id) {
        return "Thread pool: " + Thread.currentThread().getName() + "paymentInfo_fallback, id: " + id;
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("***id can't be negative number***");
        }

        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "---paymentCircuitBreaker, id: " + id + "serial: " + serialNumber;
    }

    public String paymentCircuitBreakerFallback(Integer id) {
        return "id can not be negative number, please try again later, id: " + id;
    }
}
