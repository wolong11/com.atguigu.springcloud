package com.atguigu.springcloud.alibaba.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author: zhonghua
 * @date: 4/8/2023 4:18 PM
 * @description:
 */
public interface AccountService {
    /***
     * @Author ZhongHua
     * @Description
     * @Date 4:19 PM 4/8/2023
     * @Param userId
 * @Param money
     * @Return void
    **/
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
