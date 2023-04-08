package com.atguigu.springcloud.alibaba.service;

/**
 * @author: zhonghua
 * @date: 4/8/2023 3:32 PM
 * @description:
 */
public interface StorageService {
    void decrease(Long productId, Integer count);
}
