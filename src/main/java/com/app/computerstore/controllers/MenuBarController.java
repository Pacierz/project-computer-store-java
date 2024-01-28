
package com.app.computerstore.controllers;

import com.app.computerstore.database.DatabaseConnection;
import com.app.computerstore.utils.SceneSwitch;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Klasa kontrolera obsługująca pasek menu w aplikacji sklepu komputerowego.
 * Zarządza interakcjami i zdarzeniami związanymi z menu głównego.
 */

@Slf4j
public class MenuBarController {
    @FXML private MenuBar menuBar;
    @FXML private Menu menuClients;
    @FXML private Menu menuWorkers;
    @FXML private Menu menuProducts;
    @FXML private Menu menuOrders;
    @FXML private Menu menuOrderItems;
    @FXML private Menu menuServices;
    @FXML private Menu menuDelivery;
    @FXML private Menu menuLogout;
    @FXML private Label menuClientsLabel;
    @FXML private Label menuWorkersLabel;
    @FXML private Label menuProductsLabel;
    @FXML private Label menuOrdersLabel;
    @FXML private Label menuOrderItemsLabel;
    @FXML private Label menuServicesLabel;
    @FXML private Label menuDeliveryLabel;
    @FXML private Label menuLogoutLabel;

    /**
     * Obsługa zdarzenia kliknięcia na menu "Clients".
     * Wyświetla widok zarządzania klientami.
     * @param event Zdarzenie kliknięcia na menu "Clients".
     * @throws IOException Wyjątek rzucany w przypadku problemów z przełączeniem sceny.
     */
    @FXML
    private void onClientsMenuClick(Event event) throws IOException {
        log.info("Clients");

        SceneSwitch.switchScene(event, "/clients-view.fxml");
    }

    /**
     * Obsługa zdarzenia kliknięcia na menu "Workers".
     * Wyświetla widok zarządzania pracownikami.
     * @param event Zdarzenie kliknięcia na menu "Workers".
     * @throws IOException Wyjątek rzucany w przypadku problemów z przełączeniem sceny.
     */
    @FXML
    private void onWorkersMenuClick(Event event) throws IOException {
        log.info("Workers");

        SceneSwitch.switchScene(event, "/workers-view.fxml");
    }

    /**
     * Obsługa zdarzenia kliknięcia na menu "Products".
     * Wyświetla widok zarządzania produktami.
     * @param event Zdarzenie kliknięcia na menu "Products".
     * @throws IOException Wyjątek rzucany w przypadku problemów z przełączeniem sceny.
     */
    @FXML
    private void onProductsMenuClick(Event event) throws IOException {
        log.info("Products");

        SceneSwitch.switchScene(event, "/products-view.fxml");
    }

    /**
     * Obsługa zdarzenia kliknięcia na menu "Orders".
     * Wyświetla widok zarządzania zamówieniami.
     * @param event Zdarzenie kliknięcia na menu "Orders".
     * @throws IOException Wyjątek rzucany w przypadku problemów z przełączeniem sceny.
     */
    @FXML
    private void onOrdersMenuClick(Event event) throws IOException {
        log.info("Orders");

        SceneSwitch.switchScene(event, "/orders-view.fxml");
    }

    /**
     * Obsługa zdarzenia kliknięcia na menu "OrderItems".
     * Wyświetla widok zarządzania pozycjami zamówień.
     * @param event Zdarzenie kliknięcia na menu "OrderItems".
     * @throws IOException Wyjątek rzucany w przypadku problemów z przełączeniem sceny.
     */
    @FXML
    private void onOrderItemsMenuClick(Event event) throws IOException {
        log.info("OrderItems");

        SceneSwitch.switchScene(event, "/orderItems-view.fxml");
    }

    /**
     * Obsługa zdarzenia kliknięcia na menu "Services".
     * Wyświetla widok zarządzania usługami.
     * @param event Zdarzenie kliknięcia na menu "Services".
     * @throws IOException Wyjątek rzucany w przypadku problemów z przełączeniem sceny.
     */
    @FXML
    private void onServicesMenuClick(Event event) throws IOException {
        log.info("Services");

        SceneSwitch.switchScene(event, "/services-view.fxml");
    }

    /**
     * Obsługa zdarzenia kliknięcia na menu "Delivery".
     * Wyświetla widok zarządzania dostawami.
     * @param event Zdarzenie kliknięcia na menu "Delivery".
     * @throws IOException Wyjątek rzucany w przypadku problemów z przełączeniem sceny.
     */
    @FXML
    private void onDeliveryMenuClick(Event event) throws IOException {
        log.info("Delivery");

        SceneSwitch.switchScene(event, "/delivery-view.fxml");
    }

    /**
     * Obsługa zdarzenia kliknięcia na menu "Logout".
     * Wylogowuje użytkownika i przenosi na ekran logowania.
     * @param event Zdarzenie kliknięcia na menu "Logout".
     * @throws IOException Wyjątek rzucany w przypadku problemów z przełączeniem sceny.
     */
    @FXML
    private void onLogoutMenuClick(Event event) throws IOException {
        log.info("Logout");

        DatabaseConnection.disconnect();

        SceneSwitch.switchScene(event, "/login-view.fxml");
    }
}