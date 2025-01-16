package com.app.computerstore.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Client {
    private int id;
    private String name;
    private String surname;
    private int phoneNumber;
    private String email;
    private String address;

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