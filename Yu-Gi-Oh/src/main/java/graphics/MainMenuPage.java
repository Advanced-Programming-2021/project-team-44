package graphics;

import controller.Core;
import controller.processors.Processor;
import graphics.main_menu_subPages.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class MainMenuPage extends Application {
    private static Stage stage;
    private static Pane pane;
    public HBox menuNavigationHBox;
    public Button mainMenuButton;
    public Button duelMenuButton;
    public Button deckMenuButton;
    public Button scoreboardMenuButton;
    public Button profileMenuButton;
    public Button shopMenuButton;
    public Button importExportMenuButton;


    @Override
    public void start(Stage stage) throws Exception {
        Core.trigger();
        MainMenuPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/MainMenuPage.fxml"));
        pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        menuNavigationHBox.setSpacing(0);
        menuNavigationHBox.setPrefHeight(50);

        mainMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        mainMenuButton.getStyleClass().add("button_menu_navigation_selected");

        duelMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        duelMenuButton.getStyleClass().add("button_menu_navigation");
        duelMenuButton.setOnMouseClicked(mouseEvent -> redirectToDuelPage());

        deckMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        deckMenuButton.getStyleClass().add("button_menu_navigation");
        deckMenuButton.setOnMouseClicked(mouseEvent -> redirectToDeckMenu());

        scoreboardMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        scoreboardMenuButton.getStyleClass().add("button_menu_navigation");
        scoreboardMenuButton.setOnMouseClicked(mouseEvent -> redirectToScoreboardMenu());

        profileMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        profileMenuButton.getStyleClass().add("button_menu_navigation");
        profileMenuButton.setOnMouseClicked(mouseEvent -> redirectToProfileMenu());

        shopMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        shopMenuButton.getStyleClass().add("button_menu_navigation");
        shopMenuButton.setOnMouseClicked(mouseEvent -> redirectToShopMenu());

        importExportMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        importExportMenuButton.getStyleClass().add("button_menu_navigation");
        importExportMenuButton.setOnMouseClicked(mouseEvent -> redirectToImportExportMenu());

    }

    public void redirectToDuelPage() {
        try {
            (new DuelPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redirectToDeckMenu() {
        try {
            (new DeckMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redirectToScoreboardMenu() {
        try {
            (new ScoreboardMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redirectToProfileMenu() {
        try {
            (new ProfileMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redirectToShopMenu() {
        try {
            (new ShopMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redirectToImportExportMenu() {
        try {
            (new ImportExportMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void redirectToLoginMenu() {
        try {
            (new LoginMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logoutHandler() {
        Processor.loggedInUser = null;
        redirectToLoginMenu();
    }
}
