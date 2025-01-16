package com.app.computerstore.controllers;

import com.app.computerstore.models.Delivery;
import com.app.computerstore.models.DeliveryRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class DeliveryController {
    private final DeliveryRepository deliveryRepository;

    public DeliveryController() {
        this.deliveryRepository = DeliveryRepository.getDefaultImplementation();
    }

    @FXML private MenuBar menuBar;
    @FXML private TextField textName;
    @FXML private TextField textPrice;
    @FXML private Button buttonDelete;
    @FXML private Button buttonAddWindow;
    @FXML private Button buttonAdd;
    @FXML private Button buttonCancel;
    @FXML private TableView<Delivery> tableView;
    @FXML private TableColumn<Delivery, Integer> columnId;
    @FXML private TableColumn<Delivery, String> columnName;
    @FXML private TableColumn<Delivery, Float> columnPrice;

    @FXML
    private void initialize() {
        deliveryRepository.getDelivery(tableView);
        deliveryRepository.sortById(tableView, columnId);
        deliveryRepository.editDelivery(tableView, columnName, columnPrice);
    }

    @FXML
    private void onButtonAddClick(ActionEvent actionEvent) {
        var delivery = Delivery.of(deliveryRepository.getDelivery(tableView).getLast().getId(), textName.getText(), Float.parseFloat(textPrice.getText()));
        deliveryRepository.addDelivery(delivery);
        deliveryRepository.getDelivery(tableView);
        deliveryRepository.sortById(tableView, columnId);
        onButtonCancelClick(actionEvent);
    }

    @FXML
    private void onButtonCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onButtonAddWindowClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add/delivery-add.fxml"));
        fxmlLoader.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Add Delivery");
        stage.setResizable(false);
        stage.setScene(new Scene(fxmlLoader.load(), 500, 500));
        stage.show();
        buttonAdd.setOnAction(this::onButtonAddClick);
        buttonCancel.setOnAction(this::onButtonCancelClick);
    }

    @FXML
    private void onButtonDeleteClick(ActionEvent actionEvent) {
        var delivery = tableView.getSelectionModel().getSelectedItem();
        deliveryRepository.deleteDelivery(delivery);
        deliveryRepository.getDelivery(tableView);
        deliveryRepository.sortById(tableView, columnId);
    }
}