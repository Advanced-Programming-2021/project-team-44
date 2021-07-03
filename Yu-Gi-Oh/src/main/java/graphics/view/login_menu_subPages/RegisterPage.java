package graphics.view.login_menu_subPages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class RegisterPage extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        RegisterPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/login_menu_page_subPages/RegisterPage.fxml"));
        Parent pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
