package com.app.computerstore.models;

import com.app.computerstore.database.ProductRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Interfejs definiujący repozytorium produktów.
 */

public interface ProductRepository {
    /**
     * Metoda zwracająca domyślną implementację repozytorium produktów.
     *
     * @return Domyślna implementacja repozytorium produktów.
     */
    static ProductRepository getDefaultImplementation() {
        return new ProductRepositoryImplementation();
    }
    /**
     * Metoda pobierająca listę produktów i wyświetlająca je w tabeli.
     *
     * @param tableView Tabela, do której dodawane są produkty.
     * @return Lista produktów.
     */
    ObservableList<Product> getProducts(TableView<Product> tableView);

    /**
     * Metoda dodająca nowy produkt do bazy danych.
     *
     * @param product Produkt do dodania.
     */
    void addProduct(Product product);

    /**
     * Metoda umożliwiająca edycję danych produktu w tabeli.
     *
     * @param tableView       Tabela, w której wyświetlane są produkty.
     * @param columnName      Kolumna zawierająca nazwę produktu.
     * @param columnCategory  Kolumna zawierająca kategorię produktu.
     * @param columnPrice     Kolumna zawierająca cenę produktu.
     * @param columnQuantity  Kolumna zawierająca ilość produktu.
     */
    void editProduct(TableView<Product> tableView, TableColumn<Product, String> columnName,
                     TableColumn<Product, String> columnCategory, TableColumn<Product, Float> columnPrice,
                     TableColumn<Product, Integer> columnQuantity);

    /**
     * Metoda aktualizująca dane produktu w bazie danych.
     *
     * @param product Produkt do zaktualizowania.
     */
    void updateProduct(Product product);

    /**
     * Metoda usuwająca produkt z bazy danych.
     *
     * @param product Produkt do usunięcia.
     */
    void deleteProduct(Product product);

    /**
     * Metoda sortująca produkty według identyfikatora.
     *
     * @param tableView Tabela, w której wyświetlane są produkty.
     * @param columnId  Kolumna zawierająca identyfikator produktu.
     */
    void sortById(TableView<Product> tableView, TableColumn<Product, Integer> columnId);
}