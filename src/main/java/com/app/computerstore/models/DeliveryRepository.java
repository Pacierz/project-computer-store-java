package com.app.computerstore.models;

import com.app.computerstore.database.DeliveryRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public interface DeliveryRepository {
    static DeliveryRepository getDefaultImplementation() {
        return new DeliveryRepositoryImplementation();
    }

    ObservableList<Delivery> getDelivery(TableView<Delivery> tableView);

    void addDelivery(Delivery delivery);

    void editDelivery(TableView<Delivery> tableView, TableColumn<Delivery, String> columnName, TableColumn<Delivery, Float> columnPrice);

    void updateDelivery(Delivery delivery);

    void deleteDelivery(Delivery delivery);

    void sortById(TableView<Delivery> tableView, TableColumn<Delivery, Integer> columnId);
}