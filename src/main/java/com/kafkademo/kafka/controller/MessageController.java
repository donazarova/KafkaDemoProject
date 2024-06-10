package com.kafkademo.kafka.controller;

import com.kafkademo.kafka.model.MessageDto;
import com.kafkademo.kafka.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {


    @Autowired
    private KafkaProducer producer;

    @PostMapping("/events")
    public String sendMessage(@RequestBody MessageDto message) {
        producer.send(message);
        return "message published !";
    }
}
