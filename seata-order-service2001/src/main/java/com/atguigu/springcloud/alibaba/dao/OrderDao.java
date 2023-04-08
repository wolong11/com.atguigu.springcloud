package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: zhonghua
 * @date: 4/7/2023 4:46 PM
 * @description:
 */
@Mapper
public interface OrderDao {
    // create new order
    void create(Order order);

    // update the order status, from 0 to 1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
