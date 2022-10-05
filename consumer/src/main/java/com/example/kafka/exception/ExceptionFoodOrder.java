package com.example.kafka.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionFoodOrder {
    String exceptionMessage;
    String paylaod;
}
