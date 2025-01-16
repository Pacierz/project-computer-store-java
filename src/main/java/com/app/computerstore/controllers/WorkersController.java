package com.app.computerstore.controllers;

import com.app.computerstore.models.Worker;
import com.app.computerstore.models.WorkerRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class WorkersController {
    private final WorkerRepository workerRepository;

    public WorkersController() {
        this.workerRepository = WorkerRepository.getDefaultImplementation();
    }

    @FXML private Button buttonDelete;
    @FXML private Button buttonAddWindow;
    @FXML private TextField textName;
    @FXML private TextField textSurname;
    @FXML private TextField textPhoneNumber;
    @FXML private TextField textEmail;
    @FXML private TextField textAddress;
    @FXML private TextField textSalary;
    @FXML private Button buttonAdd;
    @FXML private Button buttonCancel;
    @FXML private MenuBar menuBar;
    @FXML private TableView<Worker> tableView;
    @FXML private TableColumn<Worker, Integer> columnId;
    @FXML private TableColumn<Worker, String> columnName;
    @FXML private TableColumn<Worker, String> columnSurname;
    @FXML private TableColumn<Worker, Integer> columnPhoneNumber;
    @FXML private TableColumn<Worker, String> columnEmail;
    @FXML private TableColumn<Worker, String> columnAddress;
    @FXML private TableColumn<Worker, Integer> columnSalary;

    @FXML
    private void initialize() {
        workerRepository.getWorkers(tableView);
        workerRepository.sortById(tableView, columnId);
        workerRepository.editWorker(tableView, columnName, columnSurname, columnPhoneNumber, columnEmail, columnAddress, columnSalary);
    }

    @FXML
    private void onButtonAddClick(ActionEvent actionEvent) {
        var worker = Worker.of(workerRepository.getWorkers(tableView).getLast().getId(), textName.getText(), textSurname.getText(), Integer.parseInt(textPhoneNumber.getText()), textEmail.getText(), textAddress.getText(), Integer.parseInt(textSalary.getText()));
        workerRepository.addWorker(worker);
        workerRepository.getWorkers(tableView);
        workerRepository.sortById(tableView, columnId);
        onButtonCancelClick(actionEvent);
    }

    @FXML
    private void onButtonCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onButtonAddWindowClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add/workers-add.fxml"));
        fxmlLoader.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Add Worker");
        stage.setScene(new Scene(fxmlLoader.load(), 500, 500));
        stage.setResizable(false);
        stage.show();
        buttonAdd.setOnAction(this::onButtonAddClick);
        buttonCancel.setOnAction(this::onButtonCancelClick);
    }

    @FXML
    private void onButtonDeleteClick() {
        var worker = tableView.getSelectionModel().getSelectedItem();
        workerRepository.deleteWorker(worker);
        workerRepository.getWorkers(tableView);
        workerRepository.sortById(tableView, columnId);
    }
}