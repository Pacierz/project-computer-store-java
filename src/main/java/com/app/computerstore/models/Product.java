package com.app.computerstore.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private String category;
    private float price;
    private int quantity;

    public static Product of(int id, String name, String category, float price, int quantity) {
        validate(name, category, price, quantity);
        return Product.builder()
                .id(id)
                .name(name)
                .category(category)
                .price(price)
                .quantity(quantity)
                .build();
    }

    public static void validate(String name, String category, float price, int quantity) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
        if (category == null || category.isEmpty() || category.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Category cannot be empty or null or contain digits");
        }
        if (price < 1) {
            throw new IllegalArgumentException("Price cannot be lower than 1");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity cannot be lower than 1");
        }
    }
}