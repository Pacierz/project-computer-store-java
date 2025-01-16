package com.app.computerstore.models;

import com.app.computerstore.database.ProductRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public interface ProductRepository {
    static ProductRepository getDefaultImplementation() {
        return new ProductRepositoryImplementation();
    }

    ObservableList<Product> getProducts(TableView<Product> tableView);

    void addProduct(Product product);

    void editProduct(TableView<Product> tableView, TableColumn<Product, String> columnName,
                     TableColumn<Product, String> columnCategory, TableColumn<Product, Float> columnPrice,
                     TableColumn<Product, Integer> columnQuantity);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    void sortById(TableView<Product> tableView, TableColumn<Product, Integer> columnId);
}