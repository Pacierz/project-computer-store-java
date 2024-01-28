package com.app.computerstore.controllers;

import com.app.computerstore.models.Order;
import com.app.computerstore.models.OrderRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

/**
 * Klasa kontrolera obsługująca zarządzanie zamówieniami w aplikacji sklepu komputerowego.
 * Zarządza interakcjami i zdarzeniami związanymi z widokiem zamówień.
 */

public class OrdersController {
    private final OrderRepository orderRepository;

    /**
     * Konstruktor inicjujący kontroler zamówień.
     * Tworzy instancję repozytorium zamówień.
     */
    public OrdersController() {
        this.orderRepository = OrderRepository.getDefaultImplementation();
    }

    @FXML private TextField textClientId;
    @FXML private TextField textQuantity;
    @FXML private TextField textDeliveryId;
    @FXML private TextField textPrice;
    @FXML private DatePicker textDate;
    @FXML private TextField textStatus;
    @FXML private Button buttonDelete;
    @FXML private Button buttonAddWindow;
    @FXML private Button buttonAdd;
    @FXML private Button buttonCancel;
    @FXML private MenuBar menuBar;
    @FXML private TableView<Order> tableView;
    @FXML private TableColumn<Order, Integer> columnId;
    @FXML private TableColumn<Order, Integer> columnClientId;
    @FXML private TableColumn<Order, Integer> columnQuantity;
    @FXML private TableColumn<Order, Integer> columnDeliveryId;
    @FXML private TableColumn<Order, Float> columnPrice;
    @FXML private TableColumn<Object, java.util.Date> columnDate;
    @FXML private TableColumn<Order, String> columnStatus;

    /**
     * Metoda inicjalizująca kontroler po załadowaniu widoku FXML.
     * Pobiera listę zamówień z repozytorium i wyświetla ją w tabeli.
     */
    @FXML
    private void initialize() {
        orderRepository.getOrders(tableView);
        orderRepository.sortById(tableView, columnId);
        orderRepository.editOrder(tableView, columnClientId, columnQuantity, columnDeliveryId, columnPrice, columnDate, columnStatus);
    }

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Add".
     * Dodaje nowe zamówienie na podstawie danych wprowadzonych w polach tekstowych.
     */
    @FXML
    private void onButtonAddClick(ActionEvent actionEvent) {
        var order = Order.of(orderRepository.getOrders(tableView).getLast().getId(), Integer.parseInt(textClientId.getText()), Integer.parseInt(textQuantity.getText()), Integer.parseInt(textDeliveryId.getText()), Float.parseFloat(textPrice.getText()), Date.valueOf(textDate.getValue()), textStatus.getText());
        orderRepository.addOrder(order);
        orderRepository.getOrders(tableView);
        orderRepository.sortById(tableView, columnId);
        onButtonCancelClick(actionEvent);
    }

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Cancel".
     * Zamyka okno dodawania zamówienia.
     */
    @FXML
    private void onButtonCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Add".
     * Wyświetla okno do dodawania nowego zamówienia.
     */
    @FXML
    private void onButtonAddWindowClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add/orders-add.fxml"));
        fxmlLoader.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Add Order");
        stage.setScene(new Scene(fxmlLoader.load(), 500, 500));
        stage.setResizable(false);
        stage.show();
        buttonAdd.setOnAction(this::onButtonAddClick);
        buttonCancel.setOnAction(this::onButtonCancelClick);
    }

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Delete".
     * Usuwa wybrane zamówienie z listy.
     */
    @FXML
    private void onButtonDeleteClick(){
        var order = tableView.getSelectionModel().getSelectedItem();
        orderRepository.deleteOrder(order);
        orderRepository.getOrders(tableView);
        orderRepository.sortById(tableView, columnId);
    }
}