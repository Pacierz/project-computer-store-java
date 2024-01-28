package com.app.computerstore.database;

import com.app.computerstore.exceptions.AddException;
import com.app.computerstore.exceptions.DeleteException;
import com.app.computerstore.exceptions.GetException;
import com.app.computerstore.exceptions.UpdateException;
import com.app.computerstore.models.Order;
import com.app.computerstore.models.OrderRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * Implementacja interfejsu OrderRepository odpowiedzialnego za operacje na zamówieniach.
 * Pozwala na pobieranie, dodawanie, edytowanie i usuwanie zamówień.
 */

@Slf4j
public class OrderRepositoryImplementation implements OrderRepository {
    Connection connection = DatabaseConnection.getConnection();

    /**
     * Metoda pobierająca listę zamówień i wyświetlająca je w tabeli.
     *
     * @param tableView Tabela, do której dodawane są zamówienia.
     * @throws GetException Wyjątek, który może być rzucony w przypadku problemów z pobieraniem zamówienia.
     * @return Lista zamówień.
     */
    @Override
    public ObservableList<Order> getOrders(TableView<Order> tableView) {
        ObservableList<Order> list = null;

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, client_id, quantity, delivery_id, price, date, status FROM orders;"
            );
            ResultSet rs = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                int clientId = rs.getInt("client_id");
                int quantity = rs.getInt("quantity");
                int delivery = rs.getInt("delivery_id");
                float price = rs.getFloat("price");
                Date date = rs.getDate("date");
                String status = rs.getString("status");

                list.addAll(Order.of(id, clientId, quantity, delivery, price, date, status));
            }

            rs.close();
            ps.close();

            tableView.setItems(list);
        } catch (SQLException e) {
            throw new GetException("Error getting Orders", e);
        }

        return list;
    }

    /**
     * Metoda dodająca nowe zamówienie do bazy danych.
     *
     * @param order Zamówienie do dodania.
     * @throws AddException Wyjątek, który może być rzucony w przypadku problemów z dodawaniem zamówienia.
     */
    @Override
    public void addOrder(Order order) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO orders(client_id, quantity, delivery_id, price, date, status) VALUES (?, ?, ?, ?, ?, ?);"
            );
            ps.setInt(1, order.getClientId());
            ps.setInt(2, order.getQuantity());
            ps.setInt(3, order.getDeliveryId());
            ps.setFloat(4, order.getPrice());
            ps.setDate(5, order.getDate());
            ps.setString(6, order.getStatus());

            log.info(ps.executeUpdate() + " row added");
            ps.close();
        } catch (SQLException e) {
            throw new AddException("Error adding Order", e);
        }
    }

    /**
     * Metoda umożliwiająca edycję danych zamówienia w tabeli.
     *
     * @param tableView      Tabela, w której wyświetlane są zamówienia.
     * @param columnClientId  Kolumna zawierająca identyfikator klienta.
     * @param columnQuantity Kolumna zawierająca ilość.
     * @param columnDeliveryId Kolumna zawierająca identyfikator dostawy.
     * @param columnPrice    Kolumna zawierająca cenę.
     * @param columnDate     Kolumna zawierająca datę.
     * @param columnStatus   Kolumna zawierająca status.
     */
    @Override
    public void editOrder(TableView<Order> tableView, TableColumn<Order, Integer> columnClientId, TableColumn<Order, Integer> columnQuantity, TableColumn<Order, Integer> columnDeliveryId, TableColumn<Order, Float> columnPrice, TableColumn<Object, java.util.Date> columnDate, TableColumn<Order, String> columnStatus) {
        columnClientId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnClientId.setOnEditCommit(event -> {
            Order order = event.getTableView().getItems().get(event.getTablePosition().getRow());
            order.setClientId(event.getNewValue());
            updateOrder(order);
        });

        columnQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnQuantity.setOnEditCommit(event -> {
            Order order = event.getTableView().getItems().get(event.getTablePosition().getRow());
            order.setQuantity(event.getNewValue());
            updateOrder(order);
        });

        columnDeliveryId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnDeliveryId.setOnEditCommit(event -> {
            Order order = event.getTableView().getItems().get(event.getTablePosition().getRow());
            order.setDeliveryId(event.getNewValue());
            updateOrder(order);
        });

        columnPrice.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        columnPrice.setOnEditCommit(event -> {
            Order order = event.getTableView().getItems().get(event.getTablePosition().getRow());
            order.setPrice(event.getNewValue());
            updateOrder(order);
        });

        columnDate.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        columnDate.setOnEditCommit(event -> {
            Order order = (Order) event.getTableView().getItems().get(event.getTablePosition().getRow());
            order.setDate((Date) event.getNewValue());
            updateOrder(order);
        });

        columnStatus.setCellFactory(TextFieldTableCell.forTableColumn());
        columnStatus.setOnEditCommit(event -> {
            Order order = event.getTableView().getItems().get(event.getTablePosition().getRow());
            order.setStatus(event.getNewValue());
            updateOrder(order);
        });
    }

    /**
     * Metoda aktualizująca dane zamówienia w bazie danych.
     *
     * @param order Zamówienie do zaktualizowania.
     * @throws UpdateException Wyjątek, który może być rzucony w przypadku problemów z aktualizowaniem zamówienia.
     */
    @Override
    public void updateOrder(Order order) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE orders SET client_id=?, quantity=?, delivery_id=?, price=?, date=?, status=? WHERE id=?;"
            );
            ps.setInt(1, order.getClientId());
            ps.setInt(2, order.getQuantity());
            ps.setInt(3, order.getDeliveryId());
            ps.setFloat(4, order.getPrice());
            ps.setDate(5, order.getDate());
            ps.setString(6, order.getStatus());
            ps.setInt(7, order.getId());

            log.info(ps.executeUpdate() + " row updated");
            ps.close();
        } catch (SQLException e) {
            throw new UpdateException("Error updating Order", e);
        }
    }

    /**
     * Metoda usuwająca zamówienie z bazy danych.
     *
     * @param order Zamówienie do usunięcia.
     * @throws DeleteException Wyjątek, który może być rzucony w przypadku problemów z usuwaniem zamówienia.
     */
    @Override
    public void deleteOrder(Order order){
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM orders WHERE id = ?;"
            );
            ps.setInt(1, order.getId());
            log.info(ps.executeUpdate() + " row deleted");
            ps.close();
        } catch (SQLException e) {
            throw new DeleteException("Error deleting Order", e);
        }
    }

    /**
     * Metoda sortująca zamówienia według identyfikatora.
     *
     * @param tableView Tabela, w której wyświetlane są zamówienia.
     * @param columnId  Kolumna zawierająca identyfikator zamówienia.
     */
    @Override
    public void sortById(TableView<Order> tableView, TableColumn<Order, Integer> columnId) {
        columnId.setSortType(TableColumn.SortType.ASCENDING);
        tableView.getSortOrder().add(columnId);
        tableView.sort();
    }
}