package com.app.computerstore.models;

import lombok.*;

import java.sql.Date;
/**
 * Klasa reprezentująca zamówienie.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    /** Identyfikator zamówienia. */
    private int id;
    /** Identyfikator klienta. */
    private int clientId;
    /** Ilość zamówionego produktu. */
    private int quantity;
    /** Identyfikator dostawy. */
    private int deliveryId;
    /** Cena zamówienia. */
    private float price;
    /** Data zamówienia. */
    private Date date;
    /** Status zamówienia. */
    private String status;

    /**
     * Metoda tworząca nowe zamówienie.
     *
     * @param id         Identyfikator zamówienia.
     * @param clientId   Identyfikator klienta.
     * @param quantity   Ilość zamówionego produktu.
     * @param deliveryId Identyfikator dostawy.
     * @param price      Cena zamówienia.
     * @param date       Data zamówienia.
     * @param status     Status zamówienia.
     * @return           Obiekt klasy Order.
     */
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

    /**
     * Metoda walidująca parametry zamówienia.
     *
     * @param clientId   Identyfikator klienta.
     * @param quantity   Ilość zamówionego produktu.
     * @param deliveryId Identyfikator dostawy.
     * @param price      Cena zamówienia.
     * @param status     Status zamówienia.
     * @throws IllegalArgumentException Wyjątek rzucany gdy dane są niepoprawne.
     */
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