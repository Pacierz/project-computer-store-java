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

@Slf4j
public class ClientRepositoryImplementation implements ClientRepository {
    Connection connection = DatabaseConnection.getConnection();

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

    @Override
    public void sortById(TableView<Client> tableView, TableColumn<Client, Integer> columnId) {
        columnId.setSortType(TableColumn.SortType.ASCENDING);
        tableView.getSortOrder().add(columnId);
        tableView.sort();
    }
}