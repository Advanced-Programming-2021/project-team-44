package graphics.main_menu_subPages;

import controller.processors.Processor;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Account;

import java.net.URL;
import java.util.Objects;

public class ProfileMenuPage extends Application implements MainMenuNavigation {
    private static Stage stage;
    public HBox menuNavigationHBox;
    public Button mainMenuButton;
    public Button duelMenuButton;
    public Button deckMenuButton;
    public Button scoreboardMenuButton;
    public Button profileMenuButton;
    public Button shopMenuButton;
    public Button importExportMenuButton;
    public ImageView profilePictureImage;
    public Label nicknameLabel;
    public Label usernameLabel;
    public Label scoreLabel;
    public Label coinLabel;
    public AnchorPane anchorPane;

    @Override
    public void start(Stage stage) throws Exception {
        ProfileMenuPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/main_menu_page_subPages/ProfileMenuPage.fxml"));
        Pane pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        //Menu Navigation
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
        profileMenuButton.getStyleClass().add("button_menu_navigation_selected");

        shopMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        shopMenuButton.getStyleClass().add("button_menu_navigation");
        shopMenuButton.setOnMouseClicked(mouseEvent -> redirectToShopMenu(stage));

        importExportMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        importExportMenuButton.getStyleClass().add("button_menu_navigation");
        importExportMenuButton.setOnMouseClicked(mouseEvent -> redirectToImportExportMenu(stage));

        //Fields
        Image image;
        Account user;
        if (Processor.loggedInUser == null) user = Account.getAccountByUsername("matadysa");
        else user = Processor.loggedInUser;
        assert user != null;
        if (user.getProfilePicturePath().equals("placeholder"))
            image = new Image(Objects.requireNonNull(getClass().getResource("/static/graphics/operators/placeholder.png")).toExternalForm());
        else image = new Image(user.getProfilePicturePath());
        profilePictureImage.setImage(image);

        nicknameLabel.setText(user.getNickname());
        usernameLabel.setText(user.getUsername());
        scoreLabel.setText(String.valueOf(user.getScore()));
        coinLabel.setText(String.valueOf(user.getCoin()));
    }

    public void changeProfilePictureHandler(MouseEvent mouseEvent) {

    }
}
