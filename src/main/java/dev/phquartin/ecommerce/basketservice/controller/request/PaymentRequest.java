package dev.phquartin.ecommerce.basketservice.controller.request;

import dev.phquartin.ecommerce.basketservice.entity.PaymentMethod;

public record PaymentRequest(PaymentMethod paymentMethod) {
}
