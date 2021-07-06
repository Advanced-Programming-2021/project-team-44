package graphics;

import graphics.login_menu_subPages.CreditsPage;
import graphics.login_menu_subPages.LoginPage;
import graphics.login_menu_subPages.RegisterPage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class LoginMenuPage extends Application {
    private static Stage stage;
    private static Pane pane;
    public Button registerButton;

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenuPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/LoginMenuPage.fxml"));
        pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void registerHandler(MouseEvent mouseEvent) {
        try {
            (new RegisterPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginHandler(MouseEvent mouseEvent) {
        try {
            (new LoginPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitHandler(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void creditsHandler(MouseEvent mouseEvent) {
        try {
            (new CreditsPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}