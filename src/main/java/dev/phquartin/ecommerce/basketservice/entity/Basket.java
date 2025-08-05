package dev.phquartin.ecommerce.basketservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "basket")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Basket {

    @Id
    private String id;

    private Long client;

    private Double totalPrice;

    private List<Product> products;

    private Status status;

    public void calculateTotalPrice(){
        this.totalPrice = this.products.stream()
                .map(product -> product.getPrice() * product.getQuantity())
                .mapToDouble(Double::doubleValue).sum();
    }

}
