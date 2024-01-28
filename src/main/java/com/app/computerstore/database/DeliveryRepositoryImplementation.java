package com.app.computerstore.database;

import com.app.computerstore.exceptions.AddException;
import com.app.computerstore.exceptions.DeleteException;
import com.app.computerstore.exceptions.GetException;
import com.app.computerstore.exceptions.UpdateException;
import com.app.computerstore.models.Delivery;
import com.app.computerstore.models.DeliveryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementacja interfejsu DeliveryRepository odpowiedzialnego za operacje na dostawach.
 * Pozwala na pobieranie, dodawanie, edytowanie i usuwanie dostaw.
 */

@Slf4j
public class DeliveryRepositoryImplementation implements DeliveryRepository {
    Connection connection = DatabaseConnection.getConnection();

    /**
     * Metoda pobierająca listę dostaw i wyświetlająca je w tabeli.
     *
     * @param tableView Tabela, do której dodawane są dostawy.
     * @throws GetException Wyjątek, który może być rzucony w przypadku problemów z pobieraniem dostawy.
     * @return Lista dostaw.
     */
    @Override
    public ObservableList<Delivery> getDelivery(TableView<Delivery> tableView) {
        ObservableList<Delivery> list = null;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, name, price FROM delivery;"
            );
            ResultSet rs = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");

                list.addAll(Delivery.of(id, name, price));
            }

            rs.close();
            ps.close();

            tableView.setItems(list);
        } catch (SQLException e) {
            throw new GetException("Error getting Delivery", e);
        }

        return list;
    }

    /**
     * Metoda dodająca nową dostawę do bazy danych.
     *
     * @param delivery Dostawa do dodania.
     * @throws AddException Wyjątek, który może być rzucony w przypadku problemów z dodawaniem dostawy.
     */
    @Override
    public void addDelivery(Delivery delivery) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO delivery(name, price) VALUES (?, ?);"
            );
            ps.setString(1, delivery.getName());
            ps.setFloat(2, delivery.getPrice());

            log.info(ps.executeUpdate() + " row added");
            ps.close();
        } catch (SQLException e) {
            throw new AddException("Error adding Delivery", e);
        }
    }

    /**
     * Metoda umożliwiająca edycję danych dostawy w tabeli.
     *
     * @param tableView    Tabela, w której wyświetlane są dostawy.
     * @param columnName   Kolumna zawierająca nazwę dostawy.
     * @param columnPrice  Kolumna zawierająca cenę dostawy.
     */
    @Override
    public void editDelivery(TableView<Delivery> tableView, TableColumn<Delivery, String> columnName, TableColumn<Delivery, Float> columnPrice) {
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(event -> {
            Delivery delivery = event.getTableView().getItems().get(event.getTablePosition().getRow());
            delivery.setName(event.getNewValue());
            updateDelivery(delivery);
        });

        columnPrice.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        columnPrice.setOnEditCommit(event -> {
            Delivery delivery = event.getTableView().getItems().get(event.getTablePosition().getRow());
            delivery.setPrice(event.getNewValue());
            updateDelivery(delivery);
        });
    }

    /**
     * Metoda aktualizująca dane dostawy w bazie danych.
     *
     * @param delivery Dostawa do zaktualizowania.
     * @throws UpdateException Wyjątek, który może być rzucony w przypadku problemów z aktualizowaniem dostawy.
     */
    @Override
    public void updateDelivery(Delivery delivery) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE delivery SET name=?, price=? WHERE id=?;"
            );
            ps.setString(1, delivery.getName());
            ps.setFloat(2, delivery.getPrice());
            ps.setInt(3, delivery.getId());

            log.info(ps.executeUpdate() + " row updated");
            ps.close();
        } catch (SQLException e) {
            throw new UpdateException("Error updating Delivery", e);
        }
    }

    /**
     * Metoda usuwająca dostawę z bazy danych.
     *
     * @param delivery Dostawa do usunięcia.
     * @throws DeleteException Wyjątek, który może być rzucony w przypadku problemów z usuwaniem dostawy.
     */
    @Override
    public void deleteDelivery(Delivery delivery){
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM delivery WHERE id = ?;"
            );
            ps.setInt(1, delivery.getId());
            log.info(ps.executeUpdate() + " row deleted");
            ps.close();
        } catch (SQLException e) {
            throw new DeleteException("Error deleting Delivery", e);
        }
    }

    /**
     * Metoda sortująca dostawy według identyfikatora.
     *
     * @param tableView Tabela, w której wyświetlane są dostawy.
     * @param columnId  Kolumna zawierająca identyfikator dostawy.
     */
    @Override
    public void sortById(TableView<Delivery> tableView, TableColumn<Delivery, Integer> columnId) {
        columnId.setSortType(TableColumn.SortType.ASCENDING);
        tableView.getSortOrder().add(columnId);
        tableView.sort();
    }
}