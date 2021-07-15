package graphics.main_menu_subPages;

import controller.Core;
import graphics.main_menu_subPages.importexport_menu_subPages.ExportPage;
import graphics.main_menu_subPages.importexport_menu_subPages.ImportPage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class ImportExportMenuPage extends Application implements MainMenuNavigation {
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
        ImportExportMenuPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/main_menu_page_subPages/ImportExportMenuPage.fxml"));
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
        mainMenuButton.getStyleClass().add("button_menu_navigation");
        mainMenuButton.setOnMouseClicked(mouseEvent -> redirectToMainMenu(stage));

        duelMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        duelMenuButton.getStyleClass().add("button_menu_navigation");
        duelMenuButton.setOnMouseClicked(mouseEvent -> redirectToDuelPage(stage));

        deckMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        deckMenuButton.getStyleClass().add("button_menu_navigation");
        deckMenuButton.setOnMouseClicked(mouseEvent -> redirectToDeckMenu(stage));

        scoreboardMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        scoreboardMenuButton.getStyleClass().add("button_menu_navigation");
        scoreboardMenuButton.setOnMouseClicked(mouseEvent -> redirectToScoreboardMenu(stage));

        profileMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        profileMenuButton.getStyleClass().add("button_menu_navigation");
        profileMenuButton.setOnMouseClicked(mouseEvent -> redirectToProfileMenu(stage));

        shopMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        shopMenuButton.getStyleClass().add("button_menu_navigation");
        shopMenuButton.setOnMouseClicked(mouseEvent -> redirectToShopMenu(stage));

        importExportMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        importExportMenuButton.getStyleClass().add("button_menu_navigation_selected");


    }

    public void importHandler(MouseEvent mouseEvent) {
        try {
            (new ImportPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportHandler(MouseEvent mouseEvent) {
        try {
            (new ExportPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}