package dev.phquartin.ecommerce.basketservice.client.response;

import java.io.Serializable;

public record StoreProductResponse(Long id, String title, Double price, String description, String category, String image) implements Serializable {
}
