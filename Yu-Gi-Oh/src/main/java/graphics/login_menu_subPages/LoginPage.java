package graphics.login_menu_subPages;

import controller.Core;
import controller.processors.LoginMenuProcessor;
import graphics.GraphicalUserInterface;
import graphics.LoginMenuPage;
import graphics.MainMenuPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Menus;

import java.net.URL;
import java.util.Objects;

public class LoginPage extends Application {
    private static Stage stage;
    public PasswordField passwordField;
    public TextField usernameField;

    @Override
    public void start(Stage stage) throws Exception {
        LoginPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/login_menu_page_subPages/LoginPage.fxml"));
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

    public void redirectToMainMenu() {
        try {
            (new MainMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginSubmitHandler() {
        String response = LoginMenuProcessor.getInstance().loginUserErrorChecker(usernameField.getText(), passwordField.getText());
        GraphicalUserInterface.returnGraphicalResponse(response);
        if (Core.currentMenu == Menus.MAIN) redirectToMainMenu();
    }
}
