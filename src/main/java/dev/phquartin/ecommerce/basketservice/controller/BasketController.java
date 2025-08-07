package dev.phquartin.ecommerce.basketservice.controller;

import dev.phquartin.ecommerce.basketservice.controller.request.BasketRequest;
import dev.phquartin.ecommerce.basketservice.entity.Basket;
import dev.phquartin.ecommerce.basketservice.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService service;
    public BasketController(BasketService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Basket> create(@RequestBody BasketRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crateBasket(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable String id){
        return ResponseEntity.ok(service.getBasketById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Basket> create(@PathVariable String id, @RequestBody BasketRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateBasket(id, request));
    }

}
