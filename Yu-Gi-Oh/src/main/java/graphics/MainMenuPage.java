package graphics;

import controller.processors.Processor;
import graphics.login_menu_subPages.LoginPage;
import graphics.main_menu_subPages.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.util.Objects;

import static graphics.StartPage.stage;

public class MainMenuPage extends Application {
    private static Stage stage;
    private static Pane pane;
    public Button registerButton;

    @Override
    public void start(Stage stage) throws Exception {
        MainMenuPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/MainMenuPage.fxml"));
        pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void duelHandler(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            (new DuelPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deckHandler(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            (new DeckPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scoreboardHandler(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            (new ScoreboardPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileHandler(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            (new ProfilePage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shopHandler(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            (new ShopPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logoutHandler(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            Processor.loggedInUser = null;
            (new LoginPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
