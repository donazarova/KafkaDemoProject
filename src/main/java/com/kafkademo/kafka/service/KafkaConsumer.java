package com.kafkademo.kafka.service;


import com.kafkademo.kafka.model.MessageDto;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.topic}")
    public void read(ConsumerRecord<String, MessageDto> consumerRecord){
        String key = consumerRecord.key();
        MessageDto message = consumerRecord.value();
        log.info("Message received for key: {} value: {}", key, message.toString());
    }
}
