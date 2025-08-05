package dev.phquartin.ecommerce.basketservice.controller.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;

@Builder
public record ProductRequest(Long id,
                             @Min(value = 1, message = "Quantity must be greater than 0")
                             Integer quantity)
{}
