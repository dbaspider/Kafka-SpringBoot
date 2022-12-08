package com.sb.service;

import cn.hutool.core.thread.ThreadUtil;

import com.sb.utils.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Service
public class TestConsumer {
    private final Logger logger = LoggerFactory.getLogger(TestConsumer.class);

    @KafkaListener(id = "webGroup", topics = "topic_input")
    public void listen(List<String> input, Acknowledgment ack) {
        logger.info("thread id {} / recv msg count: {}", Thread.currentThread().getId(), input.size());
        for (String msg : input) {
            logger.info("thread id {} / input value: {}", Thread.currentThread().getId(), msg);
            ThreadUtil.safeSleep(3000L);
        }
        ack.acknowledge();
        //printThreads();
    }

//    @KafkaListener(id = "webGroup", topics = "topic_input")
//    public void listen(String input, Acknowledgment ack) {
//        logger.info("thread id {} / input value: {}", Thread.currentThread().getId(), input);
//        ack.acknowledge();
//    }

    private void printThreads() {
        // 打印当前的线程信息
        Collection<Thread> threads = ThreadUtils.getAllThreads();
        List<String> list = new ArrayList<>(128);
        //list.add("Dump Date: " + Constants.getDateStrNow());
        list.add("------------------ thread dump ------------------");
        int threadCount = 0;
        for (Thread t : threads) {
            String groupName = t.getThreadGroup() == null? "null" : t.getThreadGroup().getName();
            list.add(String.format(Locale.US, "%-15s \t %-15s \t %-15d \t %s \t %-5d \t %-15s\n",
                    t.getName(), t.getState(), t.getPriority(), t.isDaemon(), t.getId(), groupName));
            threadCount++;
        }
        list.add("------------------ total count: " + threadCount + " ------------------");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
