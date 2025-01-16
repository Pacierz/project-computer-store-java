package com.app.computerstore.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    private int id;
    private String name;
    private String surname;
    private int phoneNumber;
    private String email;
    private String address;
    private int salary;

    public static Worker of(int id, String name, String surname, int phoneNumber, String email, String address, int salary) {
        validate(name, surname, phoneNumber, email, address, salary);
        return Worker.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .phoneNumber(phoneNumber)
                .email(email)
                .address(address)
                .salary(salary)
                .build();
    }

    public static void validate(String name, String surname, int phoneNumber, String email, String address, int salary) {
        if (name.isEmpty() || name == null || name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Name cannot be empty, null or contain digits");
        }
        if (surname.isEmpty() || surname == null || surname.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Surname cannot be empty, null or contain digits");
        }
        if (phoneNumber < 99999999 || phoneNumber > 999999999) {
            throw new IllegalArgumentException("Phone number must have 9 digits");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("E-mail must contain @");
        }
        if (address.isEmpty() || address == null) {
            throw new IllegalArgumentException("Address cannot be empty or null");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be lower than 0");
        }
    }
}