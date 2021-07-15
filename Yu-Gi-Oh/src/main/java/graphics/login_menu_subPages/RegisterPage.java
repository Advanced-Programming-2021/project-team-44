package graphics.login_menu_subPages;

import controller.Core;
import controller.processors.LoginMenuProcessor;
import graphics.GraphicalUserInterface;
import graphics.LoginMenuPage;
import graphics.StartPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class RegisterPage extends Application {
    private static Stage stage;
    public TextField usernameField;
    public TextField nicknameField;
    public PasswordField passwordField;

    @Override
    public void start(Stage stage) throws Exception {
        Core.trigger();
        RegisterPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/login_menu_page_subPages/RegisterPage.fxml"));
        Parent pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void redirectToLoginMenu() {
        try {
            (new LoginMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerSubmitHandler() {
        String response = LoginMenuProcessor.getInstance().createUserErrorChecker(usernameField.getText(), nicknameField.getText(), passwordField.getText());
        GraphicalUserInterface.returnGraphicalResponse(response, stage);
    }
}
