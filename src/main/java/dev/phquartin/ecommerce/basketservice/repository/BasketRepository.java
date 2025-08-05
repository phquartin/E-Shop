package dev.phquartin.ecommerce.basketservice.repository;

import dev.phquartin.ecommerce.basketservice.entity.Basket;
import dev.phquartin.ecommerce.basketservice.entity.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketRepository extends MongoRepository<Basket, String> {
    Optional<Basket> findByClientAndStatus(Long client, Status status);
}
