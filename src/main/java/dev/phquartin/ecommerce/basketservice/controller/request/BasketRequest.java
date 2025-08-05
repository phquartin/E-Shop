package dev.phquartin.ecommerce.basketservice.controller.request;

import java.util.List;

public record BasketRequest(Long client, List<ProductRequest> products) {
}
