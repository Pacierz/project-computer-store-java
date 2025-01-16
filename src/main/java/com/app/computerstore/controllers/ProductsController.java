package com.app.computerstore.controllers;

import com.app.computerstore.models.Product;
import com.app.computerstore.models.ProductRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductsController {
    private final ProductRepository productRepository;

    public ProductsController() {
        this.productRepository = ProductRepository.getDefaultImplementation();
    }

    @FXML private TextField textName;
    @FXML private TextField textCategory;
    @FXML private TextField textPrice;
    @FXML private TextField textQuantity;
    @FXML private Button buttonDelete;
    @FXML private Button buttonAddWindow;
    @FXML private Button buttonAdd;
    @FXML private Button buttonCancel;
    @FXML private MenuBar menuBar;
    @FXML private TableView<Product> tableView;
    @FXML private TableColumn<Product, Integer> columnId;
    @FXML private TableColumn<Product, String> columnName;
    @FXML private TableColumn<Product, String> columnCategory;
    @FXML private TableColumn<Product, Float> columnPrice;
    @FXML private TableColumn<Product, Integer> columnQuantity;

    @FXML
    private void initialize() {
        productRepository.getProducts(tableView);
        productRepository.sortById(tableView, columnId);
        productRepository.editProduct(tableView, columnName, columnCategory, columnPrice, columnQuantity);
    }

    @FXML
    private void onButtonAddClick(ActionEvent actionEvent) {
        var product = Product.of(productRepository.getProducts(tableView).getLast().getId(), textName.getText(), textCategory.getText(), Float.parseFloat(textPrice.getText()), Integer.parseInt(textQuantity.getText()));
        productRepository.addProduct(product);
        productRepository.getProducts(tableView);
        productRepository.sortById(tableView, columnId);
        onButtonCancelClick(actionEvent);
    }

    @FXML
    private void onButtonCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onButtonAddWindowClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add/products-add.fxml"));
        fxmlLoader.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Add Product");
        stage.setScene(new Scene(fxmlLoader.load(), 500, 500));
        stage.setResizable(false);
        stage.show();
        buttonAdd.setOnAction(this::onButtonAddClick);
        buttonCancel.setOnAction(this::onButtonCancelClick);
    }

    @FXML
    private void onButtonDeleteClick()  {
        var product = tableView.getSelectionModel().getSelectedItem();
        productRepository.deleteProduct(product);
        productRepository.getProducts(tableView);
        productRepository.sortById(tableView, columnId);
    }
}