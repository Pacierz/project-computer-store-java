package com.app.computerstore.models;

import com.app.computerstore.database.OrderRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Interfejs definiujący repozytorium zamówień.
 */
public interface OrderRepository {
    /**
     * Metoda zwracająca domyślną implementację repozytorium zamówień.
     *
     * @return Domyślna implementacja repozytorium zamówień.
     */
    static OrderRepository getDefaultImplementation() {
        return new OrderRepositoryImplementation();
    }

    /**
     * Metoda pobierająca listę zamówień i wyświetlająca je w tabeli.
     *
     * @param tableView Tabela, do której dodawane są zamówienia.
     * @return Lista zamówień.
     */
    ObservableList<Order> getOrders(TableView<Order> tableView);

    /**
     * Metoda dodająca nowe zamówienie do bazy danych.
     *
     * @param order Zamówienie do dodania.
     */
    void addOrder(Order order);

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
    void editOrder(TableView<Order> tableView, TableColumn<Order, Integer> columnClientId,
                   TableColumn<Order, Integer> columnQuantity, TableColumn<Order, Integer> columnDeliveryId,
                   TableColumn<Order, Float> columnPrice, TableColumn<Object, java.util.Date> columnDate,
                   TableColumn<Order, String> columnStatus);

    /**
     * Metoda aktualizująca dane zamówienia w bazie danych.
     *
     * @param order Zamówienie do zaktualizowania.
     */
    void updateOrder(Order order);

    /**
     * Metoda usuwająca zamówienie z bazy danych.
     *
     * @param order Zamówienie do usunięcia.
     */
    void deleteOrder(Order order);

    /**
     * Metoda sortująca zamówienia według identyfikatora.
     *
     * @param tableView Tabela, w której wyświetlane są zamówienia.
     * @param columnId  Kolumna zawierająca identyfikator zamówienia.
     */
    void sortById(TableView<Order> tableView, TableColumn<Order, Integer> columnId);
}