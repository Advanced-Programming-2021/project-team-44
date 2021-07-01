package graphics.view.login_menu_subPages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class CreditsPage extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        CreditsPage.stage = stage;
        URL pageUrl = getClass().getResource("/static/fxml/CreditsPage.fxml");
        Parent pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}