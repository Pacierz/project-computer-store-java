package com.app.computerstore.controllers;

import com.app.computerstore.models.Service;
import com.app.computerstore.models.ServiceRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Klasa kontrolera obsługująca zarządzanie usługami w aplikacji sklepu komputerowego.
 * Zarządza interakcjami i zdarzeniami związanymi z widokiem usług.
 */

public class ServicesController {
    private final ServiceRepository serviceRepository;

    /**
     * Konstruktor inicjujący kontroler usług.
     * Tworzy instancję repozytorium usług.
     */
    public ServicesController() {
        this.serviceRepository = ServiceRepository.getDefaultImplementation();
    }

    @FXML private TextField textName;
    @FXML private TextField textPrice;
    @FXML private Button buttonDelete;
    @FXML private Button buttonAddWindow;
    @FXML private Button buttonAdd;
    @FXML private Button buttonCancel;
    @FXML private MenuBar menuBar;
    @FXML private TableView<Service> tableView;
    @FXML private TableColumn<Service, Integer> columnId;
    @FXML private TableColumn<Service, String> columnName;
    @FXML private TableColumn<Service, Float> columnPrice;

    /**
     * Metoda inicjalizująca kontroler po załadowaniu widoku FXML.
     * Pobiera listę usług z repozytorium i wyświetla ją w tabeli.
     */
    @FXML
    private void initialize() {
        serviceRepository.getServices(tableView);
        serviceRepository.sortById(tableView, columnId);
        serviceRepository.editService(tableView, columnName, columnPrice);
    }

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Add".
     * Dodaje nową usługę na podstawie danych wprowadzonych w polach tekstowych.
     */
    @FXML
    private void onButtonAddClick(ActionEvent actionEvent) {
        var service = Service.of(serviceRepository.getServices(tableView).getLast().getId(), textName.getText(), Float.parseFloat(textPrice.getText()));
        serviceRepository.addService(service);
        serviceRepository.getServices(tableView);
        serviceRepository.sortById(tableView, columnId);
        onButtonCancelClick(actionEvent);
    }

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Cancel".
     * Zamyka okno dodawania usługi.
     */
    @FXML
    private void onButtonCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Add".
     * Wyświetla okno do dodawania nowej usługi.
     */
    @FXML
    private void onButtonAddWindowClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add/services-add.fxml"));
        fxmlLoader.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Add Service");
        stage.setScene(new Scene(fxmlLoader.load(), 500, 500));
        stage.setResizable(false);
        stage.show();
        buttonAdd.setOnAction(this::onButtonAddClick);
        buttonCancel.setOnAction(this::onButtonCancelClick);
    }

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Delete".
     * Usuwa wybraną usługę z listy.
     */
    @FXML
    private void onButtonDeleteClick() {
        var service = tableView.getSelectionModel().getSelectedItem();
        serviceRepository.deleteService(service);
        serviceRepository.getServices(tableView);
        serviceRepository.sortById(tableView, columnId);
    }
}