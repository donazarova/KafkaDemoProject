package com.kafkademo.kafka.service;

import com.kafkademo.kafka.model.MessageDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Log4j2
@Service
public class KafkaProducer {

    @Value("${spring.kafka.topic}")
    private String topicName;

    private final KafkaTemplate<String, MessageDto> template;

    public KafkaProducer(KafkaTemplate<String, MessageDto> template) {
        this.template = template;
    }


    public void send(MessageDto message){
        CompletableFuture<SendResult<String, MessageDto>> future = template.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }
}
