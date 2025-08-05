package dev.phquartin.ecommerce.basketservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Product {

    private Long id;
    private String title;
    private Double price;
    private String category;
    private Integer quantity;

}
