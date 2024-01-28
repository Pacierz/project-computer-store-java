package com.app.computerstore.models;

import com.app.computerstore.database.ServiceRepositoryImplementation;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Interfejs definiujący repozytorium usług.
 */
public interface ServiceRepository {
    /**
     * Metoda zwracająca domyślną implementację repozytorium usług.
     *
     * @return Domyślna implementacja repozytorium usług.
     */
    static ServiceRepository getDefaultImplementation() {
        return new ServiceRepositoryImplementation();
    }

    /**
     * Metoda pobierająca listę usług i wyświetlająca je w tabeli.
     *
     * @param tableView TableView, do którego będą dodane usługi.
     * @return Lista usług.
     */
    ObservableList<Service> getServices(TableView<Service> tableView);

    /**
     * Metoda dodająca nową usługę.
     *
     * @param service Nowa usługa do dodania.
     */
    void addService(Service service);

    /**
     * Metoda umożliwiająca edycję usługi w tabeli.
     *
     * @param tableView   TableView, w którym zostanie edytowana usługa.
     * @param columnName  Kolumna z nazwą usługi.
     * @param columnPrice Kolumna z ceną usługi.
     */
    void editService(TableView<Service> tableView, TableColumn<Service, String> columnName, TableColumn<Service, Float> columnPrice);

    /**
     * Metoda aktualizująca dane usługi.
     *
     * @param service Usługa, której dane zostaną zaktualizowane.
     */
    void updateService(Service service);

    /**
     * Metoda usuwająca usługę.
     *
     * @param service Usługa do usunięcia.
     */
    void deleteService(Service service);

    /**
     * Metoda sortująca usługi według identyfikatora.
     *
     * @param tableView TableView, w którym będą sortowane usługi.
     * @param columnId   Kolumna z identyfikatorem usługi.
     */
    void sortById(TableView<Service> tableView, TableColumn<Service, Integer> columnId);
}