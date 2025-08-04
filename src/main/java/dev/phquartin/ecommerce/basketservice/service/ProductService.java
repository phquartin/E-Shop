package dev.phquartin.ecommerce.basketservice.service;

import dev.phquartin.ecommerce.basketservice.client.PlatziStoreClient;
import dev.phquartin.ecommerce.basketservice.client.response.PlatziProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final PlatziStoreClient client;

    public ProductService(PlatziStoreClient client) {
        this.client = client;
    }

    public List<PlatziProductResponse> getAll() {
        return client.getAllProducts();
    }

    public PlatziProductResponse getById(Long id) {
        return client.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

}
