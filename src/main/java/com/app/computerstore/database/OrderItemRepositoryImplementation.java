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

/**
 * Implementacja interfejsu OrderItemRepository odpowiedzialnego za operacje na pozycjach zamówień.
 * Pozwala na pobieranie, dodawanie, edytowanie i usuwanie pozycji zamówień.
 */

@Slf4j
public class OrderItemRepositoryImplementation implements OrderItemRepository {
    Connection connection = DatabaseConnection.getConnection();

    /**
     * Metoda pobierająca listę pozycji zamówień i wyświetlająca je w tabeli.
     *
     * @param tableView Tabela, do której dodawane są pozycje zamówień.
     * @throws GetException Wyjątek, który może być rzucony w przypadku problemów z pobieraniem pozycji zamówienia.
     * @return Lista pozycji zamówień.
     */
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

    /**
     * Metoda dodająca nową pozycję zamówienia do bazy danych.
     *
     * @param orderItem Pozycja zamówienia do dodania.
     * @throws AddException Wyjątek, który może być rzucony w przypadku problemów z dodawaniem pozycji zamówienia.
     */
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

    /**
     * Metoda umożliwiająca edycję danych pozycji zamówienia w tabeli.
     *
     * @param tableView      Tabela, w której wyświetlane są pozycje zamówień.
     * @param columnOrderId  Kolumna zawierająca identyfikator zamówienia.
     * @param columnProductId  Kolumna zawierająca identyfikator produktu.
     * @param columnQuantity Kolumna zawierająca ilość.
     * @param columnPrice    Kolumna zawierająca cenę.
     */
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

    /**
     * Metoda aktualizująca dane pozycji zamówienia w bazie danych.
     *
     * @param orderItem Pozycja zamówienia do zaktualizowania.
     * @throws UpdateException Wyjątek, który może być rzucony w przypadku problemów z aktualizowaniem pozycji zamówienia.
     */
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

    /**
     * Metoda usuwająca pozycję zamówienia z bazy danych.
     *
     * @param orderItem Pozycja zamówienia do usunięcia.
     * @throws DeleteException Wyjątek, który może być rzucony w przypadku problemów z usuwaniem pozycji zamówienia.
     */
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

    /**
     * Metoda sortująca pozycje zamówień według identyfikatora.
     *
     * @param tableView Tabela, w której wyświetlane są pozycje zamówień.
     * @param columnId  Kolumna zawierająca identyfikator pozycji zamówienia.
     */
    @Override
    public void sortById(TableView<OrderItem> tableView, TableColumn<OrderItem, Integer> columnId) {
        columnId.setSortType(TableColumn.SortType.ASCENDING);
        tableView.getSortOrder().add(columnId);
        tableView.sort();
    }
}