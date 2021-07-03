package graphics.view.login_menu_subPages;

import controller.Core;
import graphics.view.LoginMenuPage;
import graphics.view.StartPage;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class CreditsPage extends Application {
    private static Stage stage;
    public Label versionLabel;

    @Override
    public void start(Stage stage) throws Exception {
        CreditsPage.stage = stage;
        URL pageUrl = getClass().getResource("/static/fxml/login_menu_page_subPages/CreditsPage.fxml");
        Parent pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        {
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    redirectToLoginMenu(keyEvent);
                }
            });
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        versionLabel.setText(Core.version);
    }

    public void redirectToLoginMenu(KeyEvent event) {
        try {
            (new LoginMenuPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openProjectGithubRepository(MouseEvent mouseEvent) {
        try {
            Desktop.getDesktop().browse(URI.create("https://github.com/Advanced-Programming-2021/project-team-44"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openMatinGithubPage(MouseEvent mouseEvent) {
        try {
            Desktop.getDesktop().browse(URI.create("https://github.com/matadysa"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openMahdiGithubPage(MouseEvent mouseEvent) {
        try {
            Desktop.getDesktop().browse(URI.create("https://github.com/mahdikhoshdell"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
