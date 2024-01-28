package com.app.computerstore.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ServiceTest {
    Service service = new Service(0, "name", 0f);

    @Test
    void testOf() {
        Service result = Service.of(0, "name", 0f);
        Assertions.assertEquals(new Service(0, "name", 0f), result);
    }

    @Test
    void testValidate() {
        Service.validate("name", 0f);
    }

    @Test
    void testSetId() {
        service.setId(0);
    }

    @Test
    void testSetName() {
        service.setName("name");
    }

    @Test
    void testSetPrice() {
        service.setPrice(0f);
    }

    @Test
    void testBuilder() {
        Service.ServiceBuilder result = Service.builder();
        Assertions.assertEquals(null, result);
    }
}

