package com.app.computerstore.models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private int clientId;
    private int quantity;
    private int deliveryId;
    private float price;
    private Date date;
    private String status;

    public static Order of(int id, int clientId, int quantity, int deliveryId, float price, Date date, String status) {
        validate(clientId, quantity, deliveryId, price, status);
        return Order.builder()
                .id(id)
                .clientId(clientId)
                .quantity(quantity)
                .deliveryId(deliveryId)
                .price(price)
                .date(date)
                .status(status)
                .build();
    }

    public static void validate(int clientId, int quantity, int deliveryId, float price, String status) {
        if (clientId < 0) {
            throw new IllegalArgumentException("Client ID cannot be lower than 0");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be lower than 0");
        }
        if (deliveryId < 0) {
            throw new IllegalArgumentException("Delivery ID cannot be lower than 0");
        }
        if (price < 1) {
            throw new IllegalArgumentException("Price cannot be lower than 1");
        }
        if (status.isEmpty() || status == null || status.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Status cannot be empty, null or contain digits");
        }
    }
}