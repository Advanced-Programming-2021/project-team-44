package graphics.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class GraphicsView extends Application {
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GraphicsView.stage = stage;
        URL startPageUrl = getClass().getResource("/static/fxml/StartPage.fxml");
        Parent pane = FXMLLoader.load(startPageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
