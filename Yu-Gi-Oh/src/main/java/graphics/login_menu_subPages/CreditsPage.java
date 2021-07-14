package graphics.login_menu_subPages;

import controller.Core;
import graphics.LoginMenuPage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;


public class CreditsPage extends Application {
    private static Stage stage;
    public Label versionLabel;

    @Override
    public void start(Stage stage) throws Exception {
        Core.trigger();
        CreditsPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/login_menu_page_subPages/CreditsPage.fxml"));
        Parent pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        versionLabel.setText(Core.version);
    }

    public void redirectToLoginMenu() {
        try {
            (new LoginMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO github links
    public void openProjectGithubRepository() {

        try {
            Desktop.getDesktop().browse(new URI("http://www.javafx.com"));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public void openMatinGithubPage() {
        try {
            Desktop.getDesktop().browse(new URI("http://www.javafx.com"));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public void openMahdiGithubPage() {
        try {
            Desktop.getDesktop().browse(new URI("http://www.javafx.com"));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
