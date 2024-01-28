package com.app.computerstore.models;

import com.app.computerstore.database.WorkerRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Interfejs definiujący repozytorium pracowników.
 */
public interface WorkerRepository {
    /**
     * Metoda zwracająca domyślną implementację repozytorium pracowników.
     *
     * @return Domyślna implementacja repozytorium pracowników.
     */
    static WorkerRepository getDefaultImplementation() {
        return new WorkerRepositoryImplementation();
    }

    /**
     * Pobiera listę pracowników i aktualizuje widok tabeli.
     *
     * @param tableView TableView do aktualizacji.
     * @return Lista pracowników.
     */
    ObservableList<Worker> getWorkers(TableView<Worker> tableView);

    /**
     * Dodaje nowego pracownika do repozytorium.
     *
     * @param worker Nowy pracownik do dodania.
     */
    void addWorker(Worker worker);

    /**
     * Edytuje pracownika w repozytorium.
     *
     * @param tableView         TableView, który zawiera dane pracowników.
     * @param columnName        Kolumna zawierająca imię pracownika.
     * @param columnSurname     Kolumna zawierająca nazwisko pracownika.
     * @param columnPhoneNumber Kolumna zawierająca numer telefonu pracownika.
     * @param columnEmail       Kolumna zawierająca adres e-mail pracownika.
     * @param columnAddress     Kolumna zawierająca adres zamieszkania pracownika.
     * @param columnSalary      Kolumna zawierająca wynagrodzenie pracownika.
     */
    void editWorker(TableView<Worker> tableView, TableColumn<Worker, String> columnName, TableColumn<Worker, String> columnSurname, TableColumn<Worker, Integer> columnPhoneNumber, TableColumn<Worker, String> columnEmail, TableColumn<Worker, String> columnAddress, TableColumn<Worker, Integer> columnSalary);

    /**
     * Aktualizuje dane pracownika w repozytorium.
     *
     * @param worker Pracownik do zaktualizowania.
     */
    void updateWorker(Worker worker);

    /**
     * Usuwa pracownika z repozytorium.
     *
     * @param worker Pracownik do usunięcia.
     */
    void deleteWorker(Worker worker);

    /**
     * Sortuje pracowników według identyfikatora i aktualizuje widok tabeli.
     *
     * @param tableView TableView do aktualizacji.
     * @param columnId  Kolumna zawierająca identyfikator pracownika.
     */
    void sortById(TableView<Worker> tableView, TableColumn<Worker, Integer> columnId);
}
