package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: zhonghua
 * @date: 4/6/2023 3:28 PM
 * @description:
 */
@RestController
@Slf4j
public class CircuitBreakerController {
    @Value("${service-url.nacos-user-service}")
    public String serviceUrl;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")
    //@SentinelResource(value = "fallback", fallback = "handlerFallback")
    //@SentinelResource(value = "fallback", blockHandler = "handlerFlowLimit")
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "handlerFlowLimit")
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(serviceUrl + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException...");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException... the id was not be matched");
        }
        return result;
    }

    public CommonResult handlerFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, null);
        return new CommonResult(444, "handlerFallback, the exception is " + e.getMessage(), payment);
    }

    public CommonResult handlerFlowLimit(@PathVariable("id") Long id, BlockException exception) {
        Payment payment = new Payment(id, null);
        return new CommonResult(445, "block-handler, sentinel limit the flow, the exception is " + exception.getMessage(), payment);
    }

    @GetMapping("/consumer/openFeign/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        CommonResult<Payment> result = paymentService.paymentSQL(id);
        return result;
    }
}
