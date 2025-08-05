package dev.phquartin.ecommerce.basketservice.service;

import dev.phquartin.ecommerce.basketservice.client.FakeStoreClient;
import dev.phquartin.ecommerce.basketservice.client.response.StoreProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final FakeStoreClient client;

    public ProductService(FakeStoreClient client) {
        this.client = client;
    }

    public List<StoreProductResponse> getAll() {
        return client.getAllProducts();
    }

    public StoreProductResponse getById(Long id) {
        return client.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

}
