package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author: zhonghua
 * @date: 4/7/2023 11:25 AM
 * @description:
 */
@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<Payment>(4444, "this is a fallback method from PaymentFallbackService, the id is " + id, new Payment(id, "errorSerial"));
    }
}
