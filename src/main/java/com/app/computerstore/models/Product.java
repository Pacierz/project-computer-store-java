package com.app.computerstore.models;

import lombok.*;
/**
 * Klasa reprezentująca produkt.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    /** Identyfikator produktu. */
    private int id;
    /** Nazwa produktu. */
    private String name;
    /** Kategoria produktu. */
    private String category;
    /** Cena produktu. */
    private float price;
    /** Ilość produktu dostępna w magazynie. */
    private int quantity;

    /**
     * Metoda tworząca nowy produkt.
     *
     * @param id       Identyfikator produktu.
     * @param name     Nazwa produktu.
     * @param category Kategoria produktu.
     * @param price    Cena produktu.
     * @param quantity Ilość produktu dostępna w magazynie.
     * @return         Obiekt klasy Product.
     */
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

    /**
     * Metoda walidująca parametry produktu.
     *
     * @param name     Nazwa produktu.
     * @param category Kategoria produktu.
     * @param price    Cena produktu.
     * @param quantity Ilość produktu dostępna w magazynie.
     * @throws IllegalArgumentException Wyjątek rzucany gdy dane są niepoprawne.
     */
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