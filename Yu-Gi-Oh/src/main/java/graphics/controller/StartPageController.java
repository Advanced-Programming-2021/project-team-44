package graphics.controller;

import java.net.URL;
import java.util.ResourceBundle;

import graphics.view.LoginMenuPage;
import graphics.view.StartPage;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class StartPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public void redirectToLoginMenu(KeyEvent event) {
        try {
            (new LoginMenuPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}

