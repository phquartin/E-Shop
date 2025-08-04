package dev.phquartin.ecommerce.basketservice.config;

import org.springframework.http.HttpStatus;

public record ErroResponse (String message, String path, String type, String date, String status) {}
