package com.app.computerstore.models;

import com.app.computerstore.database.ServiceRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public interface ServiceRepository {
    static ServiceRepository getDefaultImplementation() {
        return new ServiceRepositoryImplementation();
    }

    ObservableList<Service> getServices(TableView<Service> tableView);

    void addService(Service service);

    void editService(TableView<Service> tableView, TableColumn<Service, String> columnName, TableColumn<Service, Float> columnPrice);

    void updateService(Service service);

    void deleteService(Service service);

    void sortById(TableView<Service> tableView, TableColumn<Service, Integer> columnId);
}