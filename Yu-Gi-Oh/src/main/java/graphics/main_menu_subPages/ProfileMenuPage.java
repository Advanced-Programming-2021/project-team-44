package graphics.main_menu_subPages;

import controller.Core;
import controller.processors.Processor;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import models.Account;

import java.io.File;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Core.trigger();
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
        if (Processor.loggedInUser == null) Processor.loggedInUser = Account.getAccountByUsername("matadysa");
        Account user = Processor.loggedInUser;
        assert user != null;

        nicknameLabel.setText(user.getNickname());
        usernameLabel.setText(user.getUsername());
        scoreLabel.setText(String.valueOf(user.getScore()));
        coinLabel.setText(String.valueOf(user.getCoin()));

        image = new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/static/graphics/operators/" + user.getProfilePicture())).toExternalForm()));
        profilePictureImage.setImage(image);

    }

    public void changeProfilePictureHandler(MouseEvent mouseEvent) {
        Popup popup = new Popup();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #8a9ce3;" +
                "-fx-background-radius: 10px;");
        anchorPane.setMaxWidth(400);
        popup.getContent().add(anchorPane);

        VBox vBox = new VBox();
        anchorPane.getChildren().add(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        //Label
        Label responseText = new Label("Choose Image:");
        vBox.getChildren().add(responseText);
        responseText.setStyle("-fx-text-alignment: center;" +
                "-fx-alignment: center;" +
                "-fx-background-color: #8a9ce3;" +
                "-fx-background-radius: 5px;" +
                "-fx-text-fill: #121D4B;" +
                "-fx-font-size: 16px;" +
                "-fx-padding: 5px;");

        //Choice Box
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        vBox.getChildren().add(choiceBox);
        File operatorsDirectory = new File("src/main/resources/static/graphics/operators");
        File[] operatorsPicturesFilesList = operatorsDirectory.listFiles();
        assert operatorsPicturesFilesList != null;
        HashMap<String, String> operatorsNamesToFilesHashMap = new HashMap<>();
        for (File file : operatorsPicturesFilesList) {
            String nameToShow = operatorNameToShow(file.getName());
            choiceBox.getItems().add(nameToShow);
            operatorsNamesToFilesHashMap.put(nameToShow, file.getName());
        }

        choiceBox.setOnAction((event) -> {
            String selectedItem = choiceBox.getSelectionModel().getSelectedItem();
            //Action
            Processor.loggedInUser.setProfilePicture(operatorsNamesToFilesHashMap.get(selectedItem));
            Image image = new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("/static/graphics/operators/" + Processor.loggedInUser.getProfilePicture())).toExternalForm()));
            profilePictureImage.setImage(image);
        });

        // OK Button
        Button okButton = new Button();
        vBox.getChildren().add(okButton);
        okButton.setText("OK");
        okButton.setStyle("-fx-text-alignment: center;" +
                "-fx-alignment: center;" +
                "-fx-trasition-duration: 0.4s;" +
                "-fx-cursor: hand;" +
                "-fx-background-color: #121D4B;" +
                "-fx-background-radius: 10px;" +
                "-fx-text-fill: #8a9ce3;" +
                "-fx-font-size: 18px;");

        popup.show(stage);
        okButton.setOnMouseClicked(mouseEvent1 -> popup.hide());
    }

    private String operatorNameToShow(String operatorFileName) {
        Pattern pattern = Pattern.compile("^(\\w+)\\.png$");
        Matcher matcher = pattern.matcher(operatorFileName);
        if (matcher.find()) {
            String[] nameSplit = matcher.group(1).split("_");
            for (String part : nameSplit)
                part = part.substring(0, 1).toUpperCase() + part.substring(1);
            StringBuilder output = new StringBuilder();
            for (String part : nameSplit)
                output.append(part).append(" ");
            output.deleteCharAt(output.length()-1);
            return output.toString();
        } else return null;
    }
}
