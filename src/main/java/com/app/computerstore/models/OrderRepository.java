package com.app.computerstore.models;

import com.app.computerstore.database.OrderRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public interface OrderRepository {
    static OrderRepository getDefaultImplementation() {
        return new OrderRepositoryImplementation();
    }

    ObservableList<Order> getOrders(TableView<Order> tableView);

    void addOrder(Order order);

    void editOrder(TableView<Order> tableView, TableColumn<Order, Integer> columnClientId,
                   TableColumn<Order, Integer> columnQuantity, TableColumn<Order, Integer> columnDeliveryId,
                   TableColumn<Order, Float> columnPrice, TableColumn<Object, java.util.Date> columnDate,
                   TableColumn<Order, String> columnStatus);

    void updateOrder(Order order);

    void deleteOrder(Order order);

    void sortById(TableView<Order> tableView, TableColumn<Order, Integer> columnId);
}