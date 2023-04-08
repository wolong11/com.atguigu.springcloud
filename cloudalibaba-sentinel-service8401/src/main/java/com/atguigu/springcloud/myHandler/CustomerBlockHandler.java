package com.atguigu.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * @author: zhonghua
 * @date: 4/6/2023 12:45 PM
 * @description:
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException1(BlockException exception) {
        return new CommonResult(4444, "by custom, global handlerException===1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444, "by custom, global handlerException===2");
    }
}
