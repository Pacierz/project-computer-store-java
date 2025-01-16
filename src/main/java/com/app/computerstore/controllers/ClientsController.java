package com.app.computerstore.controllers;

import com.app.computerstore.models.Client;
import com.app.computerstore.models.ClientRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientsController {
    private final ClientRepository clientRepository;

    public ClientsController() {
        this.clientRepository = ClientRepository.getDefaultImplementation();
    }

    @FXML private Button buttonDelete;
    @FXML private Button buttonAddWindow;
    @FXML private TextField textName;
    @FXML private TextField textSurname;
    @FXML private TextField textPhoneNumber;
    @FXML private TextField textEmail;
    @FXML private TextField textAddress;
    @FXML private Button buttonAdd;
    @FXML private Button buttonCancel;
    @FXML private TableView<Client> tableView;
    @FXML private TableColumn<Client, Integer> columnId;
    @FXML private TableColumn<Client, String> columnName;
    @FXML private TableColumn<Client, String> columnSurname;
    @FXML private TableColumn<Client, Integer> columnPhoneNumber;
    @FXML private TableColumn<Client, String> columnEmail;
    @FXML private TableColumn<Client, String> columnAddress;
    @FXML private MenuBar menuBar;

    @FXML
    private void initialize() {
        clientRepository.getClients(tableView);
        clientRepository.sortById(tableView, columnId);
        clientRepository.editClient(tableView, columnName, columnSurname, columnPhoneNumber, columnEmail, columnAddress);
    }

    @FXML
    private void onButtonAddClick(ActionEvent actionEvent) {
        var client = Client.of(clientRepository.getClients(tableView).getLast().getId(), textName.getText(), textSurname.getText(), Integer.parseInt(textPhoneNumber.getText()), textEmail.getText(), textAddress.getText());
        clientRepository.addClient(client);
        clientRepository.getClients(tableView);
        clientRepository.sortById(tableView, columnId);
        onButtonCancelClick(actionEvent);
    }

    @FXML
    private void onButtonCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onButtonAddWindowClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add/clients-add.fxml"));
        fxmlLoader.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Add Client");
        stage.setResizable(false);
        stage.setScene(new Scene(fxmlLoader.load(), 500, 500));
        stage.show();
        buttonAdd.setOnAction(this::onButtonAddClick);
        buttonCancel.setOnAction(this::onButtonCancelClick);
    }

    @FXML
    private void onButtonDeleteClick() {
        var client = tableView.getSelectionModel().getSelectedItem();
        clientRepository.deleteClient(client);
        clientRepository.getClients(tableView);
        clientRepository.sortById(tableView, columnId);
    }
}