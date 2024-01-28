package com.app.computerstore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Klasa pełni rolę punktu wejścia do aplikacji Computer Store.
*/

public class App extends Application {

    /**
     * Metoda wywoływana przy uruchamianiu aplikacji.
     * Inicjalizuje interfejs użytkownika i wyświetla główne okno logowania.
     *
     * @param stage Główny kontener aplikacji, na którym będą wyświetlane sceny.
     * @throws IOException Wyjątek rzucany w przypadku problemów z załadowaniem pliku FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Computer Store");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda rozpoczynająca działanie aplikacji.
     *
     * @param args Argumenty wiersza poleceń, nie są wykorzystywane w tym przypadku.
     */
    public static void main(String[] args) {
        launch();
    }
}