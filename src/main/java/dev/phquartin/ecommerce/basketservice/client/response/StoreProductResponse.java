package dev.phquartin.ecommerce.basketservice.client.response;

public record StoreProductResponse(Long id, String title, Double price, String description, String category, String image) {
}
