package com.app.computerstore.models;

import com.app.computerstore.database.ProductRepositoryImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductRepositoryTest {

    @Test
    void testGetDefaultImplementation() {
        ProductRepository result = ProductRepository.getDefaultImplementation();
        Assertions.assertEquals(new ProductRepositoryImplementation(), result);
    }
}

