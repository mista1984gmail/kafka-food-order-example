package com.example.kafka.service;

import com.example.kafka.domain.FoodOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SenderService {

    @Value("${kafka.topic.accept}")
    private String topicSendingFoodOrder;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    public void eventSendingFoodOrder(FoodOrder foodOrder) {
        log.info("food order request sent");
        this.kafkaTemplate.send(topicSendingFoodOrder, foodOrder);
    }
}
