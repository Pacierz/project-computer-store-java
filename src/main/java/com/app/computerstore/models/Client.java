package com.app.computerstore.models;

import lombok.*;

/**
 * Klasa reprezentująca klienta.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Client {

    /** Unikalny identyfikator klienta. */
    private int id;

    /** Imię klienta. */
    private String name;

    /** Nazwisko klienta. */
    private String surname;

    /** Numer telefonu klienta. */
    private int phoneNumber;

    /** Adres e-mail klienta. */
    private String email;

    /** Adres zamieszkania klienta. */
    private String address;

    /**
     * Metoda tworząca nowego klienta.
     *
     * @param id           Identyfikator klienta.
     * @param name         Imię klienta.
     * @param surname      Nazwisko klienta.
     * @param phoneNumber  Numer telefonu klienta.
     * @param email        Adres e-mail klienta.
     * @param address      Adres zamieszkania klienta.
     * @return             Obiekt klasy Client.
     */
    public static Client of(int id, String name, String surname, int phoneNumber, String email, String address) {
        validate(name, surname, phoneNumber, email, address);
        return Client.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .phoneNumber(phoneNumber)
                .email(email)
                .address(address)
                .build();
    }

    /**
     * Metoda walidująca parametry klienta.
     *
     * @param name         Imię klienta.
     * @param surname      Nazwisko klienta.
     * @param phoneNumber  Numer telefonu klienta.
     * @param email        Adres e-mail klienta.
     * @param address      Adres zamieszkania klienta.
     * @throws IllegalArgumentException Wyjątek rzucany gdy dane są niepoprawne.
     */
    public static void validate(String name, String surname, int phoneNumber, String email, String address) {
        if (name == null || name.isEmpty() || name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Name cannot be empty, null or cannot contain digits");
        }
        if (surname == null || surname.isEmpty() || surname.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Surname cannot be empty, null or cannot contain digits");
        }
        if (phoneNumber < 99999999 || phoneNumber > 999999999) {
            throw new IllegalArgumentException("Phone number must have 9 digits");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain @");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty or null");
        }
    }
}