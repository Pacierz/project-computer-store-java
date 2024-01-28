package com.app.computerstore.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkerTest {
    Worker worker = new Worker(0, "name", "surname", 0, "email", "address", 0);

    @Test
    void testOf() {
        Worker result = Worker.of(0, "name", "surname", 0, "email", "address", 0);
        Assertions.assertEquals(new Worker(0, "name", "surname", 0, "email", "address", 0), result);
    }

    @Test
    void testValidate() {
        Worker.validate("name", "surname", 0, "email", "address", 0);
    }

    @Test
    void testSetId() {
        worker.setId(0);
    }

    @Test
    void testSetName() {
        worker.setName("name");
    }

    @Test
    void testSetSurname() {
        worker.setSurname("surname");
    }

    @Test
    void testSetPhoneNumber() {
        worker.setPhoneNumber(0);
    }

    @Test
    void testSetEmail() {
        worker.setEmail("email");
    }

    @Test
    void testSetAddress() {
        worker.setAddress("address");
    }

    @Test
    void testSetSalary() {
        worker.setSalary(0);
    }

    @Test
    void testBuilder() {
        Worker.WorkerBuilder result = Worker.builder();
        Assertions.assertEquals(null, result);
    }
}

