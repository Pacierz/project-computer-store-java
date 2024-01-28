package com.app.computerstore.database;

import com.app.computerstore.exceptions.AddException;
import com.app.computerstore.exceptions.DeleteException;
import com.app.computerstore.exceptions.GetException;
import com.app.computerstore.exceptions.UpdateException;
import com.app.computerstore.models.Service;
import com.app.computerstore.models.ServiceRepository;
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
 * Implementacja interfejsu ServiceRepository odpowiedzialnego za operacje na usługach.
 * Pozwala na pobieranie, dodawanie, edytowanie i usuwanie usług.
 */

@Slf4j
public class ServiceRepositoryImplementation implements ServiceRepository {
    Connection connection = DatabaseConnection.getConnection();

    /**
     * Metoda pobierająca listę usług i wyświetlająca je w tabeli.
     *
     * @param tableView Tabela, do której dodawane są usługi.
     * @throws GetException Wyjątek, który może być rzucony w przypadku problemów z pobieraniem usługi.
     * @return Lista usług.
     */
    @Override
    public ObservableList<Service> getServices(TableView<Service> tableView) {
        ObservableList<Service> list = null;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, name, price FROM services;"
            );
            ResultSet rs = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");

                list.addAll(Service.of(id, name, price));
            }

            rs.close();
            ps.close();

            tableView.setItems(list);
        } catch (SQLException e) {
            throw new GetException("Error getting Services", e);
        }

        return list;
    }

    /**
     * Metoda dodająca nową usługę do bazy danych.
     *
     * @param service Usługa do dodania.
     * @throws AddException Wyjątek, który może być rzucony w przypadku problemów z dodawaniem usługi.
     */
    @Override
    public void addService(Service service) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO services(name, price) VALUES (?, ?);"
            );
            ps.setString(1, service.getName());
            ps.setFloat(2, service.getPrice());

            log.info(ps.executeUpdate() + " row inserted");
            ps.close();
        } catch (SQLException e) {
            throw new AddException("Error adding Service", e);
        }
    }

    /**
     * Metoda umożliwiająca edycję danych usługi w tabeli.
     *
     * @param tableView  Tabela, w której wyświetlane są usługi.
     * @param columnName Kolumna zawierająca nazwę usługi.
     * @param columnPrice Kolumna zawierająca cenę usługi.
     */
    @Override
    public void editService(TableView<Service> tableView, TableColumn<Service, String> columnName, TableColumn<Service, Float> columnPrice) {
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(event -> {
            Service service = event.getTableView().getItems().get(event.getTablePosition().getRow());
            service.setName(event.getNewValue());
            updateService(service);
        });

        columnPrice.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        columnPrice.setOnEditCommit(event -> {
            Service service = event.getTableView().getItems().get(event.getTablePosition().getRow());
            service.setPrice(event.getNewValue());
            updateService(service);
        });
    }

    /**
     * Metoda aktualizująca dane usługi w bazie danych.
     *
     * @param service Usługa do zaktualizowania.
     * @throws UpdateException Wyjątek, który może być rzucony w przypadku problemów z aktualizowaniem usługi.
     */
    @Override
    public void updateService(Service service){
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE services SET name=?, price=? WHERE id=?;"
            );
            ps.setString(1, service.getName());
            ps.setFloat(2, service.getPrice());
            ps.setInt(3, service.getId());

            log.info(ps.executeUpdate() + " row updated");
            ps.close();
        } catch (SQLException e) {
            throw new UpdateException("Error updating Service", e);
        }
    }

    /**
     * Metoda usuwająca usługę z bazy danych.
     *
     * @param service Usługa do usunięcia.
     * @throws DeleteException Wyjątek, który może być rzucony w przypadku problemów z usuwaniem usługi.
     */
    @Override
    public void deleteService(Service service){
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM services WHERE id = ?;"
            );
            ps.setInt(1, service.getId());
            log.info(ps.executeUpdate() + " row deleted");
            ps.close();
        } catch (SQLException e) {
            throw new DeleteException("Error deleting Service", e);
        }
    }

    /**
     * Metoda sortująca usługi według identyfikatora.
     *
     * @param tableView Tabela, w której wyświetlane są usługi.
     * @param columnId  Kolumna zawierająca identyfikator usługi.
     */
    @Override
    public void sortById(TableView<Service> tableView, TableColumn<Service, Integer> columnId) {
        columnId.setSortType(TableColumn.SortType.ASCENDING);
        tableView.getSortOrder().add(columnId);
        tableView.sort();
    }
}