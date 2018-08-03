/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author root
 */
@RestController
public class OneController {

    @Autowired
    RabbitTemplate r;

    @RequestMapping("/addcart")
    public String addcart() {
        String a = (String) r.receiveAndConvert("mq0");
        r.convertAndSend("Mq1", "MQ1", a);
        r.convertAndSend("Mq4", "MQ4", "a");
        return a;
    }
}
