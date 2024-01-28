package com.app.computerstore.models;

import com.app.computerstore.database.ClientRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Interfejs definiujący repozytorium klientów.
 */
public interface ClientRepository {

    /**
     * Metoda zwracająca domyślną implementację repozytorium klientów.
     *
     * @return Domyślna implementacja repozytorium klientów.
     */
    static ClientRepository getDefaultImplementation() {
        return new ClientRepositoryImplementation();
    }

    /**
     * Pobiera listę klientów z bazy danych i wyświetla w tabeli.
     *
     * @param tableView Tabela, do której dodawane są dane.
     * @return Lista klientów.
     */
    ObservableList<Client> getClients(TableView<Client> tableView);

    /**
     * Dodaje nowego klienta do bazy danych.
     *
     * @param client Nowy klient do dodania.
     */
    void addClient(Client client);

    /**
     * Umożliwia edycję danych klienta w tabeli.
     *
     * @param tableView     Tabela, w której edytowane są dane.
     * @param columnName    Kolumna z imieniem klienta.
     * @param columnSurname Kolumna z nazwiskiem klienta.
     * @param columnPhoneNumber Kolumna z numerem telefonu klienta.
     * @param columnEmail    Kolumna z adresem email klienta.
     * @param columnAddress  Kolumna z adresem klienta.
     */
    void editClient(TableView<Client> tableView, TableColumn<Client, String> columnName,
                    TableColumn<Client, String> columnSurname, TableColumn<Client, Integer> columnPhoneNumber,
                    TableColumn<Client, String> columnEmail, TableColumn<Client, String> columnAddress);

    /**
     * Aktualizuje dane klienta w bazie danych po edycji.
     *
     * @param client Edytowany klient.
     */
    void updateClient(Client client);

    /**
     * Usuwa klienta z bazy danych.
     *
     * @param client Klient do usunięcia.
     */
    void deleteClient(Client client);

    /**
     * Sortuje tabelę klientów względem identyfikatora klienta.
     *
     * @param tableView Tabela do posortowania.
     * @param columnId  Kolumna z identyfikatorem klienta.
     */
    void sortById(TableView<Client> tableView, TableColumn<Client, Integer> columnId);
}