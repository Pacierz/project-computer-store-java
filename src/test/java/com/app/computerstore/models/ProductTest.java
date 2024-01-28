package com.app.computerstore.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {
    Product product = new Product(0, "name", "category", 0f, 0);

    @Test
    void testOf() {
        Product result = Product.of(0, "name", "category", 0f, 0);
        Assertions.assertEquals(new Product(0, "name", "category", 0f, 0), result);
    }

    @Test
    void testValidate() {
        Product.validate("name", "category", 0f, 0);
    }

    @Test
    void testSetId() {
        product.setId(0);
    }

    @Test
    void testSetName() {
        product.setName("name");
    }

    @Test
    void testSetCategory() {
        product.setCategory("category");
    }

    @Test
    void testSetPrice() {
        product.setPrice(0f);
    }

    @Test
    void testSetQuantity() {
        product.setQuantity(0);
    }

    @Test
    void testBuilder() {
        Product.ProductBuilder result = Product.builder();
        Assertions.assertEquals(null, result);
    }
}

