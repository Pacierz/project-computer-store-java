package com.app.computerstore.controllers;

import com.app.computerstore.models.OrderItem;
import com.app.computerstore.models.OrderItemRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Klasa kontrolera obsługująca zarządzanie pozycjami zamówień w aplikacji sklepu komputerowego.
 * Zarządza interakcjami i zdarzeniami związanymi z widokiem pozycji zamówień.
 */

public class OrderItemsController {
    private final OrderItemRepository orderItemRepository;

    /**
     * Konstruktor inicjujący kontroler pozycji zamówień.
     * Tworzy instancję repozytorium pozycji zamówień.
     */
    public OrderItemsController() {
        this.orderItemRepository = OrderItemRepository.getDefaultImplementation();
    }

    @FXML private MenuBar menuBar;
    @FXML private Button buttonAdd;
    @FXML private Button buttonCancel;
    @FXML private Button buttonDelete;
    @FXML private Button buttonAddWindow;
    @FXML private TextField textOrderId;
    @FXML private TextField textProductId;
    @FXML private TextField textQuantity;
    @FXML private TextField textPrice;
    @FXML private TableView<OrderItem> tableView;
    @FXML private TableColumn<OrderItem, Integer> columnId;
    @FXML private TableColumn<OrderItem, Integer> columnOrderId;
    @FXML private TableColumn<OrderItem, Integer> columnProductId;
    @FXML private TableColumn<OrderItem, Integer> columnQuantity;
    @FXML private TableColumn<OrderItem, Float> columnPrice;

    /**
     * Metoda inicjalizująca kontroler po załadowaniu widoku FXML.
     * Pobiera listę pozycji zamówień z repozytorium i wyświetla ją w tabeli.
     */
    @FXML
    private void initialize() {
        orderItemRepository.getOrderItems(tableView);
        orderItemRepository.sortById(tableView, columnId);
        orderItemRepository.editOrderItem(tableView, columnOrderId, columnProductId, columnQuantity, columnPrice);
    }

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Add".
     * Dodaje nową pozycję zamówienia na podstawie danych wprowadzonych w polach tekstowych.
     */
    @FXML
    private void onButtonAddClick(ActionEvent actionEvent) {
        var orderItem = OrderItem.of(orderItemRepository.getOrderItems(tableView).getLast().getId(), Integer.parseInt(textOrderId.getText()), Integer.parseInt(textProductId.getText()), Integer.parseInt(textQuantity.getText()), Float.parseFloat(textPrice.getText()));

        orderItemRepository.addOrderItem(orderItem);
        orderItemRepository.getOrderItems(tableView);
        orderItemRepository.sortById(tableView, columnId);
        onButtonCancelClick(actionEvent);
    }

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Cancel".
     * Zamyka okno dodawania pozycji zamówienia.
     */
    @FXML
    private void onButtonCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Add".
     * Wyświetla okno do dodawania nowej pozycji zamówienia.
     */
    @FXML
    private void onButtonAddWindowClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add/orderItems-add.fxml"));
        fxmlLoader.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Add OrderItem");
        stage.setScene(new Scene(fxmlLoader.load(), 500, 500));
        stage.setResizable(false);
        stage.show();
        buttonAdd.setOnAction(this::onButtonAddClick);
        buttonCancel.setOnAction(this::onButtonCancelClick);
    }

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Delete".
     * Usuwa wybraną pozycję zamówienia z listy.
     */
    @FXML
    private void onButtonDeleteClick() {
        OrderItem orderItem = tableView.getSelectionModel().getSelectedItem();
        orderItemRepository.deleteOrderItem(orderItem);
        orderItemRepository.getOrderItems(tableView);
        orderItemRepository.sortById(tableView, columnId);
    }
}