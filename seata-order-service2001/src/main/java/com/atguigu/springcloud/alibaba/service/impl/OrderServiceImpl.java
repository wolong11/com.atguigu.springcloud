package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderDao;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: zhonghua
 * @date: 4/8/2023 11:12 AM
 * @description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("====>start to create new order");
        orderDao.create(order);

        log.info("====>the order micro-service start to call the storage micro-service, to reduce the storage count");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("====>complete call the storage micro-service");

        log.info("====>the order micro-service start to call the account micro-service, to reduce the account's balance");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("====>complete call the account micro-service");

        log.info("====>start to update the order status");
        orderDao.update(order.getUserId(), 0);
        log.info("====>complete update the order status");

        log.info("complete create the new order");

    }
}
