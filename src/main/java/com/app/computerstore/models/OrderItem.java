package com.app.computerstore.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private float price;

    public static OrderItem of(int id, int orderId, int productId, int quantity, float price) {
        validate(orderId, productId, quantity, price);
        return OrderItem.builder()
                .id(id)
                .orderId(orderId)
                .productId(productId)
                .quantity(quantity)
                .price(price)
                .build();
    }

    public static void validate(int orderId, int productId, int quantity, float price) {
        if (orderId < 0) {
            throw new IllegalArgumentException("Order ID cannot be lower than 0");
        }
        if (productId < 0) {
            throw new IllegalArgumentException("Product ID cannot be lower than 0");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity cannot be lower than 1");
        }
        if (price < 1) {
            throw new IllegalArgumentException("Price cannot be lower than 1");
        }
    }
}