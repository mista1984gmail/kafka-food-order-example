package com.example.kafka.service;

import com.example.kafka.domain.FoodOrder;
import com.example.kafka.exception.ValidationExceptionOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MessagingService {

    @KafkaListener(topics = "${kafka.topic.accept}",
            groupId = "${spring.kafka.consumer.group-id}",
            errorHandler = "myOrderErrorHandler")
    public void consumeMessage(@Payload FoodOrder foodOrder, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic)   {
        if(foodOrder.getItem().isEmpty()){
            throw new ValidationExceptionOrder("Item must be not null");
        } else if (foodOrder.getAmount() <= 0) {
            throw new ValidationExceptionOrder("Amount must be not positive");
        } else {
            log.info("message consumed {}, from topic: {}", foodOrder, topic);
        }
    }
}
