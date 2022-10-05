package com.example.kafka.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "myOrderErrorHandler")
public class FoodOrderErrorHandler implements ConsumerAwareListenerErrorHandler {
    @Value("${kafka.topic.exception}")
    private String topicSendingExceptionFoodOrder;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        log.warn("Food order error: {}, because : {}", message.getPayload(),
                exception.getMessage());
        kafkaTemplate.send(topicSendingExceptionFoodOrder, new ExceptionFoodOrder(exception.getMessage(), message.getPayload().toString()));
        return null;
    }
}
