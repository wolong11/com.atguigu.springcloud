package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author: zhonghua
 * @date: 2/8/2023 2:36 PM
 * @description:
 */
public interface PaymentService {

    public int createPayment(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
