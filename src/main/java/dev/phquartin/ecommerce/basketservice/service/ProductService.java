package dev.phquartin.ecommerce.basketservice.service;

import dev.phquartin.ecommerce.basketservice.client.FakeStoreClient;
import dev.phquartin.ecommerce.basketservice.client.response.StoreProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    private final FakeStoreClient client;

    public ProductService(FakeStoreClient client) {
        this.client = client;
    }

    @Cacheable("products")
    public List<StoreProductResponse> getAll() {
        return client.getAllProducts();
    }

    @Cacheable(value = "product", key = "#productId")
    public StoreProductResponse getById(Long productId) {
        log.info("Fetching product with id: {}", productId); // Testando Cache
        return client.getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
    }

}
