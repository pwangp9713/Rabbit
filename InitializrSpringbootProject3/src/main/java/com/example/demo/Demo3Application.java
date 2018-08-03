package com.example.demo;

import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Demo3Application {

    @Autowired
    RabbitTemplate r;

    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class, args);
    }

    @Async
    @RabbitListener(queues = "mq1")
    public Message recieve(Message m) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Demo3Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        String a = new String(m.getBody());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!     " + a + "     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        r.convertAndSend("Mq2", "MQ2", a);
        return m;
    }
}
