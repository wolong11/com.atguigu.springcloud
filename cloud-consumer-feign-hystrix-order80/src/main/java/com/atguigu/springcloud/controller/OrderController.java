package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhonghua
 * @date: 3/23/2023 12:58 PM
 * @description:
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalHandler")
public class OrderController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("result===>>>" + result);
        return result;
    }

    //@HystrixCommand(fallbackMethod = "paymentInfoTimeoutFallbackMethod", commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    //})
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        int a = 10 / 0;
        String result = paymentService.paymentInfo_timeout(id);
        log.info("result===>>>" + result);
        return result;
    }

    public String paymentInfoTimeoutFallbackMethod(@PathVariable("id") Integer id) {
        return "consumer80, payment service busy or has problem itself";
    }

    public String paymentGlobalHandler() {
        return "Global service fallback handler";
    }
}
