package com.example.demo;

import com.example.demo.domain.Bill;
import com.example.demo.mapper.BillMapper;
import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("com.example.demo.mapper")
public class Demo4Application {

    @Autowired
    RabbitTemplate r;

    BillMapper b;

    public Demo4Application(BillMapper b) {
        this.b = b;
    }

    public static void main(String[] args) {
        SpringApplication.run(Demo4Application.class, args);
    }

    @Async
    @RabbitListener(queues = "mq2")
    public Message recieve(Message m) {
        String a = new String(m.getBody());
        System.out.println("????????????????????????????????????????????????????    " + a + "   ?????????????????????????????????????????????????????????");
        Integer id = Integer.parseInt(a);
        Bill bb = b.checkbill(id);
        if (bb == null) {
            r.convertAndSend("Mq3", "MQ3", a);
        } else {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    " + a + "   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
        return m;
    }
}
