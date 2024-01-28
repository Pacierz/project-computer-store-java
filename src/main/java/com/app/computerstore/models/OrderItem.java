package com.app.computerstore.models;

import lombok.*;

/**
 * Klasa reprezentująca pozycję zamówienia.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    /** Identyfikator pozycji zamówienia. */
    private int id;
    /** Identyfikator zamówienia. */
    private int orderId;
    /** Identyfikator produktu. */
    private int productId;
    /** Ilość produktów. */
    private int quantity;
    /** Cena jednostkowa produktu. */
    private float price;

    /**
     * Metoda tworząca nową pozycję zamówienia.
     *
     * @param id         Identyfikator pozycji zamówienia.
     * @param orderId    Identyfikator zamówienia.
     * @param productId  Identyfikator produktu.
     * @param quantity   Ilość produktów.
     * @param price      Cena jednostkowa produktu.
     * @return           Obiekt klasy OrderItem
     */
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

    /**
     * Metoda walidująca parametry pozycji zamówienia.
     *
     * @param orderId    Identyfikator zamówienia.
     * @param productId  Identyfikator produktu.
     * @param quantity   Ilość produktów.
     * @param price      Cena jednostkowa produktu.
     * @throws IllegalArgumentException Wyjątek rzucany gdy dane są niepoprawne.
     */
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