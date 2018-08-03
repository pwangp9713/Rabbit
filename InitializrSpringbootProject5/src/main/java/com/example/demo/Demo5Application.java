package com.example.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableRabbit
@SpringBootApplication
public class Demo5Application {

    @Autowired
    RabbitTemplate r;

    public static void main(String[] args) {
        SpringApplication.run(Demo5Application.class, args);
    }

    @Async
    @RabbitListener(queues = "mq3")
    public Message recieve(Message m) {
        String a = new String(m.getBody());
        System.out.println("0000000000000000000000000000000000000000000000000000    " + a + "   00000000000000000000000000000000000000000000000000000");
        r.convertAndSend("Mq0", "MQ0", a);
        r.convertAndSend("Mq4", "MQ4", "aa");

        return m;
    }

}
