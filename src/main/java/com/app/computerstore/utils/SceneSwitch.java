package com.app.computerstore.utils;

import com.app.computerstore.App;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Klasa umożliwiająca przełączanie scen w aplikacji JavaFX.
 */
public class SceneSwitch {

    /**
     * Metoda switchScene służy do przełączania scen w odpowiedzi na zdarzenie.
     *
     * @param event    Zdarzenie, które wywołuje przełączenie sceny.
     * @param fxmlName Nazwa pliku FXML reprezentującego nową scenę.
     * @throws IOException Wyjątek, który może być rzucony w przypadku problemów z ładowaniem pliku FXML.
     */
    public static void switchScene(Event event, String fxmlName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
