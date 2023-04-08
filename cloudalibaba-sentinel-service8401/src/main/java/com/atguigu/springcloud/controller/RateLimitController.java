package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.myHandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhonghua
 * @date: 4/5/2023 6:02 PM
 * @description:
 */
@RestController
public class RateLimitController {

    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleExcpetion")
    public CommonResult byResource() {
        return new CommonResult(200, "service success by resource name rate limit", new Payment(2023L, "serial001"));
    }

    public CommonResult<String> handleExcpetion(BlockException exception) {
        return new CommonResult(444, "service can not be useful" + "\t" + exception.getClass().getCanonicalName());
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource("byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "service success by url rate limit", new Payment(2023L, "serial002"));
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "custom===", new Payment(2023L, "serial003"));
    }

}
