package graphics;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class GraphicalUserInterface {
    private static final Stage stage;

    static {
        stage = StartPage.stage;
    }

    public static void returnGraphicalResponse(String response) {
        Popup popup = new Popup();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #8a9ce3;" +
                "-fx-background-radius: 10px 10px;");
        anchorPane.setMaxWidth(400);
        popup.getContent().add(anchorPane);

        VBox vBox = new VBox();
        anchorPane.getChildren().add(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        Label responseText = new Label(response);
        vBox.getChildren().add(responseText);
        responseText.setStyle("-fx-text-alignment: center;" +
                "-fx-alignment: center;" +
                "-fx-background-color: #8a9ce3;" +
                "-fx-text-fill: #121D4B;" +
                "-fx-font-size: 16px;" +
                "-fx-padding: 5px;");


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
        okButton.setOnMouseClicked(mouseEvent -> popup.hide());
    }

}
