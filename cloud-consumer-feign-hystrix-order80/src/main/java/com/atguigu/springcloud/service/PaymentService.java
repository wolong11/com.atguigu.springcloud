package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: zhonghua
 * @date: 3/23/2023 12:57 PM
 * @description:
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE", fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_timeout(@PathVariable("id") Integer id);
}
