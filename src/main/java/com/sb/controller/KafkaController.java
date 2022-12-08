package com.sb.controller;

import com.sb.service.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    private final Producer producer;

    @Autowired
    public KafkaController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping(value = "/publish")
    public void sendMessage(@RequestParam("message") String message) {
        logger.info("sendMessage: {}", message);
        this.producer.sendMessage(message);
    }
}