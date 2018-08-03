/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author root
 */
@RestController
public class BuildController {

    @Autowired
    AmqpAdmin a;
    @Autowired
    RabbitTemplate r;

    @RequestMapping("/prepare")
    public String prepare() {
        a.declareExchange(new DirectExchange("Mq0", true, false));
        a.declareQueue(new Queue("mq0", true));
        a.declareBinding(new Binding("mq0", Binding.DestinationType.QUEUE, "Mq0", "MQ0", null));
        r.convertAndSend("Mq0", "MQ0", "1");
        r.convertAndSend("Mq0", "MQ0", "2");
        r.convertAndSend("Mq0", "MQ0", "3");
        r.convertAndSend("Mq0", "MQ0", "4");
        r.convertAndSend("Mq0", "MQ0", "5");
        r.convertAndSend("Mq0", "MQ0", "6");
        r.convertAndSend("Mq0", "MQ0", "7");
        r.convertAndSend("Mq0", "MQ0", "8");
        r.convertAndSend("Mq0", "MQ0", "9");
        r.convertAndSend("Mq0", "MQ0", "10");
        
        a.declareExchange(new DirectExchange("Mq1", true, false));
        a.declareQueue(new Queue("mq1", true));
        a.declareBinding(new Binding("mq1", Binding.DestinationType.QUEUE, "Mq1", "MQ1", null));
        
        a.declareExchange(new DirectExchange("Mq2", true, false));
        a.declareQueue(new Queue("mq2", true));
        a.declareBinding(new Binding("mq2", Binding.DestinationType.QUEUE, "Mq2", "MQ2", null));
        
        a.declareExchange(new DirectExchange("Mq3", true, false));
        a.declareQueue(new Queue("mq3", true));
        a.declareBinding(new Binding("mq3", Binding.DestinationType.QUEUE, "Mq3", "MQ3", null));
        
        a.declareExchange(new DirectExchange("Mq4", true, false));
        a.declareQueue(new Queue("mq4", true));
        a.declareBinding(new Binding("mq4", Binding.DestinationType.QUEUE, "Mq4", "MQ4", null));
        
        return "success";
    }
}
