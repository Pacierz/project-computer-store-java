
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

    @FXML
    private void onClientsMenuClick(Event event) throws IOException {
        log.info("Clients");

        SceneSwitch.switchScene(event, "/clients-view.fxml");
    }

    @FXML
    private void onWorkersMenuClick(Event event) throws IOException {
        log.info("Workers");

        SceneSwitch.switchScene(event, "/workers-view.fxml");
    }

    @FXML
    private void onProductsMenuClick(Event event) throws IOException {
        log.info("Products");

        SceneSwitch.switchScene(event, "/products-view.fxml");
    }

    @FXML
    private void onOrdersMenuClick(Event event) throws IOException {
        log.info("Orders");

        SceneSwitch.switchScene(event, "/orders-view.fxml");
    }

    @FXML
    private void onOrderItemsMenuClick(Event event) throws IOException {
        log.info("OrderItems");

        SceneSwitch.switchScene(event, "/orderItems-view.fxml");
    }

    @FXML
    private void onServicesMenuClick(Event event) throws IOException {
        log.info("Services");

        SceneSwitch.switchScene(event, "/services-view.fxml");
    }

    @FXML
    private void onDeliveryMenuClick(Event event) throws IOException {
        log.info("Delivery");

        SceneSwitch.switchScene(event, "/delivery-view.fxml");
    }

    @FXML
    private void onLogoutMenuClick(Event event) throws IOException {
        log.info("Logout");

        DatabaseConnection.disconnect();

        SceneSwitch.switchScene(event, "/login-view.fxml");
    }
}