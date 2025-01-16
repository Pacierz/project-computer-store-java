package com.app.computerstore.database;

import com.app.computerstore.exceptions.AddException;
import com.app.computerstore.exceptions.DeleteException;
import com.app.computerstore.exceptions.GetException;
import com.app.computerstore.exceptions.UpdateException;
import com.app.computerstore.models.OrderItem;
import com.app.computerstore.models.OrderItemRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class OrderItemRepositoryImplementation implements OrderItemRepository {
    Connection connection = DatabaseConnection.getConnection();

    @Override
    public ObservableList<OrderItem> getOrderItems(TableView<OrderItem> tableView) {
        ObservableList<OrderItem> list = null;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, order_id, product_id, quantity, price FROM orders_items;"
            );
            ResultSet rs = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                int orderId = rs.getInt("order_id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");

                list.addAll(OrderItem.of(id, orderId, productId, quantity, price));
            }

            rs.close();
            ps.close();

            tableView.setItems(list);
        } catch (SQLException e) {
            throw new GetException("Error getting OrderItems", e);
        }

        return list;
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO orders_items(order_id, product_id, quantity, price) VALUES (?, ?, ?, ?);"
            );
            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getProductId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setFloat(4, orderItem.getPrice());

            log.info(ps.executeUpdate() + " row added");
            ps.close();
        } catch (SQLException e) {
            throw new AddException("Error adding OrderItem", e);
        }
    }

    @Override
    public void editOrderItem(TableView<OrderItem> tableView, TableColumn<OrderItem, Integer> columnOrderId, TableColumn<OrderItem, Integer> columnProductId, TableColumn<OrderItem, Integer> columnQuantity, TableColumn<OrderItem, Float> columnPrice) {
        columnOrderId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnOrderId.setOnEditCommit(event -> {
            OrderItem orderItem = event.getTableView().getItems().get(event.getTablePosition().getRow());
            orderItem.setOrderId(event.getNewValue());
            updateOrderItem(orderItem);
        });

        columnProductId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnProductId.setOnEditCommit(event -> {
            OrderItem orderItem = event.getTableView().getItems().get(event.getTablePosition().getRow());
            orderItem.setProductId(event.getNewValue());
            updateOrderItem(orderItem);
        });

        columnQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnQuantity.setOnEditCommit(event -> {
            OrderItem orderItem = event.getTableView().getItems().get(event.getTablePosition().getRow());
            orderItem.setQuantity(event.getNewValue());
            updateOrderItem(orderItem);
        });

        columnPrice.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        columnPrice.setOnEditCommit(event -> {
            OrderItem orderItem = event.getTableView().getItems().get(event.getTablePosition().getRow());
            orderItem.setPrice(event.getNewValue());
            updateOrderItem(orderItem);
        });
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE orders_items SET order_id=?, product_id=?, quantity=?, price=? WHERE id=?;"
            );
            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getProductId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setFloat(4, orderItem.getPrice());
            ps.setInt(5, orderItem.getId());


            log.info(ps.executeUpdate() + " row updated");
            ps.close();
        } catch (SQLException e) {
            throw new UpdateException("Error updating OrderItem", e);
        }
    }

    @Override
    public void deleteOrderItem(OrderItem orderItem) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM orders_items WHERE id = ?;"
            );
            ps.setInt(1, orderItem.getId());
            log.info(ps.executeUpdate() + " row deleted");
            ps.close();
        } catch (SQLException e) {
            throw new DeleteException("Error deleting OrderItem", e);
        }
    }

    @Override
    public void sortById(TableView<OrderItem> tableView, TableColumn<OrderItem, Integer> columnId) {
        columnId.setSortType(TableColumn.SortType.ASCENDING);
        tableView.getSortOrder().add(columnId);
        tableView.sort();
    }
}