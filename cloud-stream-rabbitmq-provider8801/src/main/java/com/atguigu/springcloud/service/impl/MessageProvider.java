package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author: zhonghua
 * @date: 3/29/2023 4:30 PM
 * @description:
 */
@EnableBinding(Source.class)
@Slf4j
public class MessageProvider implements IMessageProvider {

    @Resource
    private MessageChannel output;
 
    @Override
    public String send() {
        String serail = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serail).build());
        log.info("serial===>>>"+serail);
        return null;
    }
}
