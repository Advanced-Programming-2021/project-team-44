package graphics.main_menu_subPages;

import controller.Core;
import controller.processors.Processor;
import controller.processors.ShopMenuProcessor;
import graphics.GraphicalUserInterface;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Account;
import models.cards.Card;

import java.net.URL;
import java.util.Objects;

public class ShopMenuPage extends Application implements MainMenuNavigation {
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
    public ChoiceBox<String> cardsChoiceBox;
    public ImageView cardImage;
    public Button butButton;
    public Button forceBuyButton;

    @Override
    public void start(Stage stage) throws Exception {
        Core.trigger();
        ShopMenuPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/main_menu_page_subPages/ShopMenuPage.fxml"));
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
        shopMenuButton.getStyleClass().add("button_menu_navigation_selected");

        importExportMenuButton.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/static/graphics/css_styles/button_style.css")).toExternalForm());
        importExportMenuButton.getStyleClass().add("button_menu_navigation");
        importExportMenuButton.setOnMouseClicked(mouseEvent -> redirectToImportExportMenu(stage));

        //Fields
        if (Processor.loggedInUser == null) Processor.loggedInUser = Account.getAccountByUsername("matadysa");
        Image defaultImage = new Image(Objects.requireNonNull(getClass().getResource("/static/graphics/cards/back/cardsBack.png")).toExternalForm());
        cardImage.setImage(defaultImage);
        for (Card card : Card.allCards)
            cardsChoiceBox.getItems().add(card.getName());
        cardsChoiceBox.setOnAction((event) -> {
            String selectedItem = cardsChoiceBox.getSelectionModel().getSelectedItem();
            //Action
            Image image = new Image(Objects.requireNonNull(getClass().getResource("/static/graphics/cards/all/" + selectedItem + ".jpg")).toExternalForm());
            cardImage.setImage(image);
        });

    }

    public void buyHandler() {
        String response = ShopMenuProcessor.getInstance().buyCardErrorChecker(cardsChoiceBox.getSelectionModel().getSelectedItem());
        GraphicalUserInterface.returnGraphicalResponse(response, stage);
    }

    public void forceBuyHandler() {
        String response = ShopMenuProcessor.getInstance().forceBuyCard(cardsChoiceBox.getSelectionModel().getSelectedItem());
        GraphicalUserInterface.returnGraphicalResponse(response, stage);
    }
}
