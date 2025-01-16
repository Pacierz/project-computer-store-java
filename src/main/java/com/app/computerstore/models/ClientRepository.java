package com.app.computerstore.models;

import com.app.computerstore.database.ClientRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public interface ClientRepository {

    static ClientRepository getDefaultImplementation() {
        return new ClientRepositoryImplementation();
    }

    ObservableList<Client> getClients(TableView<Client> tableView);

    void addClient(Client client);

    void editClient(TableView<Client> tableView, TableColumn<Client, String> columnName,
                    TableColumn<Client, String> columnSurname, TableColumn<Client, Integer> columnPhoneNumber,
                    TableColumn<Client, String> columnEmail, TableColumn<Client, String> columnAddress);

    void updateClient(Client client);

    void deleteClient(Client client);

    void sortById(TableView<Client> tableView, TableColumn<Client, Integer> columnId);
}