package graphics.view;

import graphics.controller.StartPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class StartPage extends Application {
    public static Stage stage;
    private static final StartPageController controller = new StartPageController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StartPage.stage = stage;
        {
            Image icon = new Image((Objects.requireNonNull(getClass().getResource("/static/graphics/icon/game_icon.png"))).toExternalForm());
            stage.getIcons().add(icon);
            stage.setTitle("Yu-Gi-Oh!");
        }
        URL startPageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/StartPage.fxml"));
        Parent pane = FXMLLoader.load(startPageUrl);
        Scene scene = new Scene(pane);
        {
            scene.setOnKeyPressed(controller::redirectToLoginMenu);
        }
        stage.setScene(scene);
        stage.show();
    }
}
