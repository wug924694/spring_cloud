package com.kgc.springcloud.service.impl;

import com.kgc.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;


@EnableBinding(Source.class)  //定义消息推送的通道
public class MessageProviderImpl implements IMessageProvider {
    @Resource
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {
        String string = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(string).build());
        System.out.println("发送的内容为：------ "+string);
        return null;
    }
}
