package com.app.computerstore.models;

import com.app.computerstore.database.WorkerRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public interface WorkerRepository {
    static WorkerRepository getDefaultImplementation() {
        return new WorkerRepositoryImplementation();
    }

    ObservableList<Worker> getWorkers(TableView<Worker> tableView);

    void addWorker(Worker worker);

    void editWorker(TableView<Worker> tableView, TableColumn<Worker, String> columnName, TableColumn<Worker, String> columnSurname, TableColumn<Worker, Integer> columnPhoneNumber, TableColumn<Worker, String> columnEmail, TableColumn<Worker, String> columnAddress, TableColumn<Worker, Integer> columnSalary);

    void updateWorker(Worker worker);

    void deleteWorker(Worker worker);

    void sortById(TableView<Worker> tableView, TableColumn<Worker, Integer> columnId);
}
