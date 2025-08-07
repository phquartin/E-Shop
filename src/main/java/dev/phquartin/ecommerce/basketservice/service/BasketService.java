package dev.phquartin.ecommerce.basketservice.service;

import dev.phquartin.ecommerce.basketservice.client.response.StoreProductResponse;
import dev.phquartin.ecommerce.basketservice.controller.request.BasketRequest;
import dev.phquartin.ecommerce.basketservice.controller.request.PaymentRequest;
import dev.phquartin.ecommerce.basketservice.entity.Basket;
import dev.phquartin.ecommerce.basketservice.entity.Product;
import dev.phquartin.ecommerce.basketservice.entity.Status;
import dev.phquartin.ecommerce.basketservice.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    private final BasketRepository repository;
    private final ProductService productService;
    public BasketService(BasketRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    public Basket crateBasket(BasketRequest request){

        repository.findByClientAndStatus(request.client(), Status.OPENED)
                .ifPresent(p -> {
                    throw new IllegalArgumentException("A basket already exists for this client: " + p.getClient());
                });

        List<Product> products = createProductsList(request);
        Basket basket = Basket.builder()
                .client(request.client())
                .products(products)
                .status(Status.OPENED)
                .build();
        basket.calculateTotalPrice();
        return repository.save(basket);
    }

    public Basket getBasketById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Basket not found with id: " + id));
    }

    public Basket updateBasket(String id, BasketRequest request) {
        List<Product> products = createProductsList(request);
        Basket basket = getBasketById(id);
        basket.getProducts().clear();
        basket.setProducts(products);
        basket.calculateTotalPrice();
        return repository.save(basket);
    }

    public Basket payBasket(String id, PaymentRequest request) {
        Basket basket = getBasketById(id);
        basket.setPaymentMethod(request.paymentMethod());
        basket.setStatus(Status.SOLD);
        return repository.save(basket);
    }

    private List<Product> createProductsList(BasketRequest request) {
        List<Product> products = new java.util.ArrayList<>();
        request.products().forEach(productRequest -> {
            StoreProductResponse storeProductResponse = productService.getById(productRequest.id());
            Product productEntity = Product.builder()
                    .id(storeProductResponse.id())
                    .title(storeProductResponse.title())
                    .price(storeProductResponse.price())
                    .category(storeProductResponse.category())
                    .quantity(productRequest.quantity())
                    .build();
            products.add(productEntity);
        });
        return products;
    }
}
