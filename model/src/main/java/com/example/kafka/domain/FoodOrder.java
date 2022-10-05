package com.example.kafka.domain;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class FoodOrder {
    private String item;
    @Positive
    private Double amount;
}
