package com.sb.service;

import com.sb.utils.ToolHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

//    @KafkaListener(topics = "topic_1", groupId = "biz_group1")
//    public void consume(String message) {
//        logger.info(String.format("$$ -> Consumed Message -> %s", message));
//    }

    @KafkaListener(topics = "helloTest", groupId = "hello_group1")
    public void consumeHello(String message, Acknowledgment ack) {
        logger.info("-> thread: {}/{} consumeHello -> {}", Thread.currentThread().getId(), Thread.currentThread().getName(), message);
        ack.acknowledge();
        ToolHelper.printThreads();
    }
}