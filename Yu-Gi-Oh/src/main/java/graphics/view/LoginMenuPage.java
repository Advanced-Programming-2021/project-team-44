package graphics.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class LoginMenuPage extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenuPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/LoginMenuPage.fxml"));
        Parent pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}


