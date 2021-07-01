package graphics.view;

import graphics.controller.StartPageController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;

public class StartPage extends Application {
    public static Stage stage;
    private static StartPageController controller = new StartPageController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StartPage.stage = stage;
        URL startPageUrl = getClass().getResource("/static/fxml/StartPage.fxml");
        Parent pane = FXMLLoader.load(startPageUrl);
        Scene scene = new Scene(pane);
        {
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    controller.redirectToLoginMenu(keyEvent);
                }
            });
        }
        stage.setScene(scene);
        stage.show();
    }
}
