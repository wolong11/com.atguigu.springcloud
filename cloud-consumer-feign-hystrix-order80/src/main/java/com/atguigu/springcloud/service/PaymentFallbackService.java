package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author: zhonghua
 * @date: 3/23/2023 4:35 PM
 * @description:
 */
@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService--paymentInfo_OK";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "PaymentFallbackService--paymentInfo_timeout";
    }
}
