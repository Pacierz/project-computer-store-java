package com.app.computerstore.models;

import lombok.*;

/**
 * Klasa reprezentująca dostawę.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Delivery {
    /** Identyfikator dostawy. */
    private int id;
    /** Nazwa dostawy. */
    private String name;
    /** Cena dostawy. */
    private float price;

    /**
     * Metoda tworząca nową dostawę.
     *
     * @param id    Identyfikator dostawy.
     * @param name  Nazwa dostawy.
     * @param price Cena dostawy.
     * @return      Obiekt klasy Delivery.
     */
    public static Delivery of(int id, String name, float price) {
        validate(name, price);
        return Delivery.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
    }

    /**
     * Metoda walidująca parametry dostawy.
     *
     * @param name  Nazwa dostawy.
     * @param price Cena dostawy.
     * @throws IllegalArgumentException Wyjątek rzucany gdy dane są niepoprawne.
     */
    public static void validate(String name, float price) {
        if (name == null || name.isEmpty() || name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Name cannot be empty, null or contain digits");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be lower than 0");
        }
    }
}