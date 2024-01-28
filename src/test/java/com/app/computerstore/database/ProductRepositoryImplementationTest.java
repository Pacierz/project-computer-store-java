package com.app.computerstore.database;

import com.app.computerstore.models.Product;
import com.app.computerstore.models.ProductRepository;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.sql.Connection;

import static org.mockito.Mockito.*;

class ProductRepositoryImplementationTest {
    @Mock
    Connection connection;
    @Mock
    Logger log;
    @InjectMocks
    ProductRepositoryImplementation productRepositoryImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProducts() {
        ObservableList<Product> result = productRepositoryImplementation.getProducts(null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testAddProduct() {
        productRepositoryImplementation.addProduct(new Product(0, "name", "category", 0f, 0));
    }

    @Test
    void testEditProduct() {
        productRepositoryImplementation.editProduct(null, null, null, null, null);
    }

    @Test
    void testUpdateProduct() {
        productRepositoryImplementation.updateProduct(new Product(0, "name", "category", 0f, 0));
    }

    @Test
    void testDeleteProduct() {
        productRepositoryImplementation.deleteProduct(new Product(0, "name", "category", 0f, 0));
    }

    @Test
    void testSortById() {
        productRepositoryImplementation.sortById(null, null);
    }

    @Test
    void testGetDefaultImplementation() {
        ProductRepository result = ProductRepository.getDefaultImplementation();
        Assertions.assertEquals(new ProductRepositoryImplementation(), result);
    }
}

