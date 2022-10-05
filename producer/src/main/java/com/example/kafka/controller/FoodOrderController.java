package com.example.kafka.controller;


import com.example.kafka.domain.FoodOrder;
import com.example.kafka.service.SenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class FoodOrderController {

    private final SenderService senderService;

    @PostMapping
    public FoodOrder createFoodOrder(@RequestBody FoodOrder foodOrder) {
        log.info("food order request received");
        senderService.eventSendingFoodOrder(foodOrder);
        return foodOrder;
}
}
