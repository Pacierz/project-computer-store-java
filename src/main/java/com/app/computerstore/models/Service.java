package com.app.computerstore.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Service {
    private int id;
    private String name;
    private float price;

    public static Service of(int id, String name, float price) {
        validate(name, price);
        return Service.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
    }

    public static void validate(String name, float price) {
        if (name.isEmpty() || name == null || name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Name cannot be empty, null or contain digits");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be lower than 0");
        }
    }
}