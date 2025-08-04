package dev.phquartin.ecommerce.basketservice.client;

import dev.phquartin.ecommerce.basketservice.client.response.PlatziProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "PlatziStoreClient", url = "${platzi.store.client.api}")
public interface PlatziStoreClient {

    @GetMapping("/products")
    List<PlatziProductResponse> getAllProducts();

    @GetMapping("/products/{id}")
    Optional<PlatziProductResponse> getProductById(@PathVariable Long id);

}
