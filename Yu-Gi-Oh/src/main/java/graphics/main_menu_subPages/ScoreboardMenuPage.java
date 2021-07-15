package graphics.main_menu_subPages;

import controller.Core;
import controller.processors.ScoreboardMenuProcessor;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Account;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ScoreboardMenuPage extends Application implements MainMenuNavigation {
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
    public ScrollPane scoreboardScrollPane;

    @Override
    public void start(Stage stage) throws Exception {
        Core.trigger();
        ScoreboardMenuPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/main_menu_page_subPages/ScoreboardMenuPage.fxml"));
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
        scoreboardMenuButton.getStyleClass().add("button_menu_navigation_selected");

        profileMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        profileMenuButton.getStyleClass().add("button_menu_navigation");
        profileMenuButton.setOnMouseClicked(mouseEvent -> redirectToProfileMenu(stage));

        shopMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        shopMenuButton.getStyleClass().add("button_menu_navigation");
        shopMenuButton.setOnMouseClicked(mouseEvent -> redirectToShopMenu(stage));

        importExportMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        importExportMenuButton.getStyleClass().add("button_menu_navigation");
        importExportMenuButton.setOnMouseClicked(mouseEvent -> redirectToImportExportMenu(stage));

        //Fields
        Background background = new Background(new BackgroundFill(null, null, null));
        scoreboardScrollPane.setBackground(background);

        VBox vBox = new VBox();
        vBox.setSpacing(0);
        scoreboardScrollPane.setContent(vBox);

        LinkedHashMap<Integer, Account> rankingsHashmap = ScoreboardMenuProcessor.getInstance().showScoreboard();
        for (Map.Entry entry : rankingsHashmap.entrySet()) {
            vBox.getChildren().add(scoreboardItem((Integer) entry.getKey(), (Account) entry.getValue()));
        }

    }

    private HBox scoreboardItem(Integer rank, Account account) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_LEFT);
        hBox.setSpacing(10);
        hBox.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/hBox_style.css")).toExternalForm());
        hBox.getStyleClass().add("hBox_scoreboard_item");

        //Rank Label
        Label rankLabel = new Label(rank.toString());
        rankLabel.setPrefWidth(100);
        hBox.getChildren().add(rankLabel);
        rankLabel.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/label_style.css")).toExternalForm());
        hBox.getStyleClass().add("label_scoreboard_item");

        //Nickname Label
        Label nicknameLabel = new Label(account.getNickname());
        nicknameLabel.setPrefWidth(280);
        hBox.getChildren().add(nicknameLabel);
        nicknameLabel.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/label_style.css")).toExternalForm());
        nicknameLabel.getStyleClass().add("label_scoreboard_item");

        //Score Label
        Label scoreLabel = new Label(String.valueOf(account.getScore()));
        scoreLabel.setPrefWidth(100);
        hBox.getChildren().add(scoreLabel);
        scoreLabel.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/label_style.css")).toExternalForm());
        scoreLabel.getStyleClass().add("label_scoreboard_item");

        return hBox;
    }
}
