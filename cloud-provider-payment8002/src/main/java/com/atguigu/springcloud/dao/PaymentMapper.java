package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: zhonghua
 * @date: 2/8/2023 1:20 PM
 * @description:
 */
@Mapper
public interface PaymentMapper {

    public int createPayment(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
