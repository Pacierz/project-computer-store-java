package com.app.computerstore.models;

import com.app.computerstore.database.OrderItemRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Interfejs definiujący repozytorium pozycji zamówienia.
 */
public interface OrderItemRepository {
    /**
     * Metoda zwracająca domyślną implementację repozytorium pozycji zamówień.
     *
     * @return Domyślna implementacja repozytorium pozycji zamówień.
     */
    static OrderItemRepository getDefaultImplementation() {
        return new OrderItemRepositoryImplementation();
    }

    /**
     * Metoda pobierająca listę pozycji zamówień i wyświetlająca je w tabeli.
     *
     * @param tableView Tabela, do której dodawane są pozycje zamówień.
     * @return Lista pozycji zamówień.
     */
    ObservableList<OrderItem> getOrderItems(TableView<OrderItem> tableView);

    /**
     * Metoda dodająca nową pozycję zamówienia do bazy danych.
     *
     * @param orderItem Pozycja zamówienia do dodania.
     */
    void addOrderItem(OrderItem orderItem);

    /**
     * Metoda umożliwiająca edycję danych pozycji zamówienia w tabeli.
     *
     * @param tableView      Tabela, w której wyświetlane są pozycje zamówień.
     * @param columnOrderId  Kolumna zawierająca identyfikator zamówienia.
     * @param columnProductId  Kolumna zawierająca identyfikator produktu.
     * @param columnQuantity Kolumna zawierająca ilość.
     * @param columnPrice    Kolumna zawierająca cenę.
     */
    void editOrderItem(TableView<OrderItem> tableView, TableColumn<OrderItem, Integer> columnOrderId,
                       TableColumn<OrderItem, Integer> columnProductId, TableColumn<OrderItem, Integer> columnQuantity,
                       TableColumn<OrderItem, Float> columnPrice);

    /**
     * Metoda aktualizująca dane pozycji zamówienia w bazie danych.
     *
     * @param orderItem Pozycja zamówienia do zaktualizowania.
     */
    void updateOrderItem(OrderItem orderItem);

    /**
     * Metoda usuwająca pozycję zamówienia z bazy danych.
     *
     * @param orderItem Pozycja zamówienia do usunięcia.
     */
    void deleteOrderItem(OrderItem orderItem);

    /**
     * Metoda sortująca pozycje zamówień według identyfikatora.
     *
     * @param tableView Tabela, w której wyświetlane są pozycje zamówień.
     * @param columnId  Kolumna zawierająca identyfikator pozycji zamówienia.
     */
    void sortById(TableView<OrderItem> tableView, TableColumn<OrderItem, Integer> columnId);
}