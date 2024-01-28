package com.app.computerstore.models;

import lombok.*;

/**
 * Klasa reprezentująca usługę.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Service {
    /** Identyfikator usługi. */
    private int id;
    /** Nazwa usługi. */
    private String name;
    /** Cena usługi. */
    private float price;

    /**
     * Metoda tworząca nową usługę.
     *
     * @param id    Identyfikator usługi.
     * @param name  Nazwa usługi.
     * @param price Cena usługi.
     * @return      Obiekt klasy Service.
     */
    public static Service of(int id, String name, float price) {
        validate(name, price);
        return Service.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
    }

    /**
     * Metoda walidująca parametry usługi.
     *
     * @param name  Nazwa usługi.
     * @param price Cena usługi.
     * @throws IllegalArgumentException Wyjątek rzucany gdy dane są niepoprawne.
     */
    public static void validate(String name, float price) {
        if (name.isEmpty() || name == null || name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Name cannot be empty, null or contain digits");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be lower than 0");
        }
    }
}