package dev.phquartin.ecommerce.basketservice.client;

import dev.phquartin.ecommerce.basketservice.client.response.StoreProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "FakeStoreClient", url = "${fake.store.api}")
public interface FakeStoreClient {

    @GetMapping("/products")
    List<StoreProductResponse> getAllProducts();

    @GetMapping("/products/{id}")
    Optional<StoreProductResponse> getProductById(@PathVariable Long id);

}
