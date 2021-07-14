package graphics;

import controller.Core;
import controller.processors.LoginMenuProcessor;
import graphics.login_menu_subPages.CreditsPage;
import graphics.login_menu_subPages.LoginPage;
import graphics.login_menu_subPages.RegisterPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class LoginMenuPage extends Application {
    private static Stage stage;
    private static Pane pane;

    @Override
    public void start(Stage stage) throws Exception {
        Core.trigger();
        LoginMenuPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/LoginMenuPage.fxml"));
        pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void registerHandler() {
        try {
            (new RegisterPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginHandler() {
        try {
            (new LoginPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitHandler() {
        LoginMenuProcessor.getInstance().exitMenu();
    }

    public void creditsHandler() {
        try {
            (new CreditsPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}