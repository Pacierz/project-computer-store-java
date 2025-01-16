package com.app.computerstore.models;

import com.app.computerstore.database.OrderItemRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public interface OrderItemRepository {
    static OrderItemRepository getDefaultImplementation() {
        return new OrderItemRepositoryImplementation();
    }
    ObservableList<OrderItem> getOrderItems(TableView<OrderItem> tableView);
    void addOrderItem(OrderItem orderItem);
    void editOrderItem(TableView<OrderItem> tableView, TableColumn<OrderItem, Integer> columnOrderId,
                       TableColumn<OrderItem, Integer> columnProductId, TableColumn<OrderItem, Integer> columnQuantity,
                       TableColumn<OrderItem, Float> columnPrice);

    void updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(OrderItem orderItem);
    void sortById(TableView<OrderItem> tableView, TableColumn<OrderItem, Integer> columnId);
}