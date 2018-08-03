/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author root
 */
@RestController
public class TwoController {

    int a = 10;

    @Async
    @RabbitListener(queues = "mq4")
    public Message recieve(Message m) {
        String s = new String(m.getBody());
        System.out.println("***************************************************     " + s + "   *******************************************");
        if (s.length() > 1) {
            a = a + 1;
        }
        if (s.length() == 1) {
            a = a - 1;
        }
        return m;
    }

    @RequestMapping("/limit")
    public int limit() {
        return a;
    }
}
