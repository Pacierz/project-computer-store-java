package com.app.computerstore.database;

import com.app.computerstore.exceptions.AddException;
import com.app.computerstore.exceptions.DeleteException;
import com.app.computerstore.exceptions.GetException;
import com.app.computerstore.exceptions.UpdateException;
import com.app.computerstore.models.Client;
import com.app.computerstore.models.ClientRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementacja interfejsu ClientRepository odpowiedzialnego za operacje na klientach.
 * Pozwala na pobieranie, dodawanie, edytowanie i usuwanie klientów.
 */

@Slf4j
public class ClientRepositoryImplementation implements ClientRepository {
    Connection connection = DatabaseConnection.getConnection();

    /**
     * Metoda pobierająca listę klientów i wyświetlająca ich w tabeli.
     *
     * @param tableView Tabela, do której dodawani są klienci.
     * @throws GetException Wyjątek, który może być rzucony w przypadku problemów z pobieraniem klienta.
     * @return Lista klientów.
     */
    @Override
    public ObservableList<Client> getClients(TableView<Client> tableView) {
        ObservableList<Client> list = null;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, name, surname, phone_number, email, address FROM clients;"
            );
            ResultSet rs = ps.executeQuery();

            list = FXCollections.observableArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int phoneNumber = rs.getInt("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");

                list.addAll(Client.of(id, name, surname, phoneNumber, email, address));
            }

            rs.close();
            ps.close();

            tableView.setItems(list);
        } catch (SQLException e) {
            throw new GetException("Error getting Clients", e);
        }
        return list;
    }

    /**
     * Metoda dodająca nowego klienta do bazy danych.
     *
     * @param client Klient do dodania.
     * @throws AddException Wyjątek, który może być rzucony w przypadku problemów z dodawaniem klienta.
     */
    @Override
    public void addClient(Client client) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO clients(name, surname, phone_number, email, address) VALUES (?, ?, ?, ?, ?);"
            );
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setInt(3, client.getPhoneNumber());
            ps.setString(4, client.getEmail());
            ps.setString(5, client.getAddress());

            log.info(ps.executeUpdate() + " row inserted");
            ps.close();
        } catch (SQLException e) {
            throw new AddException("Error adding Client", e);
        }
    }

    /**
     * Metoda umożliwiająca edycję danych klienta w tabeli.
     *
     * @param tableView       Tabela, w której wyświetleni są klienci.
     * @param columnName      Kolumna zawierająca imię klienta.
     * @param columnSurname   Kolumna zawierająca nazwisko klienta.
     * @param columnPhoneNumber Kolumna zawierająca numer telefonu klienta.
     * @param columnEmail     Kolumna zawierająca adres e-mail klienta.
     * @param columnAddress   Kolumna zawierająca adres klienta.
     */
    @Override
    public void editClient(TableView<Client> tableView, TableColumn<Client, String> columnName, TableColumn<Client, String> columnSurname, TableColumn<Client, Integer> columnPhoneNumber, TableColumn<Client, String> columnEmail, TableColumn<Client, String> columnAddress) {
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(event -> {
            Client client = event.getTableView().getItems().get(event.getTablePosition().getRow());
            client.setName(event.getNewValue());
            updateClient(client);
        });

        columnSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        columnSurname.setOnEditCommit(event -> {
            Client client = event.getTableView().getItems().get(event.getTablePosition().getRow());
            client.setSurname(event.getNewValue());
            updateClient(client);
        });

        columnPhoneNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnPhoneNumber.setOnEditCommit(event -> {
            Client client = event.getTableView().getItems().get(event.getTablePosition().getRow());
            client.setPhoneNumber(event.getNewValue());
            updateClient(client);
        });

        columnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        columnEmail.setOnEditCommit(event -> {
            Client client = event.getTableView().getItems().get(event.getTablePosition().getRow());
            client.setEmail(event.getNewValue());
            updateClient(client);
        });

        columnAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        columnAddress.setOnEditCommit(event -> {
            Client client = event.getTableView().getItems().get(event.getTablePosition().getRow());
            client.setAddress(event.getNewValue());
            updateClient(client);
        });
    }

    /**
     * Metoda aktualizująca dane klienta w bazie danych.
     *
     * @param client Klient do zaktualizowania.
     * @throws UpdateException Wyjątek, który może być rzucony w przypadku problemów z aktualizowaniem klienta.
     */
    @Override
    public void updateClient(Client client) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE clients SET name=?, surname=?, phone_number=?, email=?, address=? WHERE id=?;"
            );
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setInt(3, client.getPhoneNumber());
            ps.setString(4, client.getEmail());
            ps.setString(5, client.getAddress());
            ps.setInt(6, client.getId());

            log.info(ps.executeUpdate() + " row updated");
            ps.close();
        } catch (SQLException e) {
            throw new UpdateException("Error updating Client", e);
        }
    }

    /**
     * Metoda usuwająca klienta z bazy danych.
     *
     * @param client Klient do usunięcia.
     * @throws DeleteException Wyjątek, który może być rzucony w przypadku problemów z usuwaniem klienta.
     */
    @Override
    public void deleteClient(Client client) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM clients WHERE id = ?;"
            );
            ps.setInt(1, client.getId());
            log.info(ps.executeUpdate() + " row deleted");
            ps.close();
        } catch (SQLException e) {
            throw new DeleteException("Error deleting Client", e);
        }
    }

    /**
     * Metoda sortująca pracowników według identyfikatora.
     *
     * @param tableView Tabela, w której wyświetleni są klienci.
     * @param columnId  Kolumna zawierająca identyfikator klienta.
     */
    @Override
    public void sortById(TableView<Client> tableView, TableColumn<Client, Integer> columnId) {
        columnId.setSortType(TableColumn.SortType.ASCENDING);
        tableView.getSortOrder().add(columnId);
        tableView.sort();
    }
}