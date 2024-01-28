package com.app.computerstore.models;

import com.app.computerstore.database.DeliveryRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Interfejs definiujący repozytorium dostaw.
 */
public interface DeliveryRepository {
    /**
     * Metoda zwracająca domyślną implementację repozytorium dostaw.
     *
     * @return Domyślna implementacja repozytorium dostaw.
     */
    static DeliveryRepository getDefaultImplementation() {
        return new DeliveryRepositoryImplementation();
    }

    /**
     * Metoda pobierająca listę dostaw i wyświetlająca je w tabeli.
     *
     * @param tableView Tabela, do której dodawane są dostawy.
     * @return Lista dostaw.
     */
    ObservableList<Delivery> getDelivery(TableView<Delivery> tableView);

    /**
     * Metoda dodająca nową dostawę do bazy danych.
     *
     * @param delivery Dostawa do dodania.
     */
    void addDelivery(Delivery delivery);

    /**
     * Metoda umożliwiająca edycję danych dostawy w tabeli.
     *
     * @param tableView    Tabela, w której wyświetlane są dostawy.
     * @param columnName   Kolumna zawierająca nazwę dostawy.
     * @param columnPrice  Kolumna zawierająca cenę dostawy.
     */
    void editDelivery(TableView<Delivery> tableView, TableColumn<Delivery, String> columnName, TableColumn<Delivery, Float> columnPrice);

    /**
     * Metoda aktualizująca dane dostawy w bazie danych.
     *
     * @param delivery Dostawa do zaktualizowania.
     */
    void updateDelivery(Delivery delivery);

    /**
     * Metoda usuwająca dostawę z bazy danych.
     *
     * @param delivery Dostawa do usunięcia.
     */
    void deleteDelivery(Delivery delivery);

    /**
     * Metoda sortująca dostawy według identyfikatora.
     *
     * @param tableView Tabela, w której wyświetlane są dostawy.
     * @param columnId  Kolumna zawierająca identyfikator dostawy.
     */
    void sortById(TableView<Delivery> tableView, TableColumn<Delivery, Integer> columnId);
}