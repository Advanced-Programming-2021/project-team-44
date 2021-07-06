package graphics.login_menu_subPages;

import controller.processors.LoginMenuProcessor;
import controller.processors.Processor;
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
import models.Menus;

import java.net.URL;
import java.util.Objects;

public class RegisterPage extends Application {
    private static Stage stage;
    public TextField usernameField;
    public TextField nicknameField;
    public PasswordField passwordField;

    @Override
    public void start(Stage stage) throws Exception {
        RegisterPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/login_menu_page_subPages/RegisterPage.fxml"));
        Parent pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void redirectToLoginMenu() {
        try {
            (new LoginMenuPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerSubmitHandler() {
        StringBuilder command = new StringBuilder();
        command.append("-u ").append(usernameField.getText()).append(" ");
        command.append("-n ").append(nicknameField.getText()).append(" ");
        command.append("-p ").append(passwordField.getText());
        String response = ((LoginMenuProcessor) Objects.requireNonNull(Processor.getProcessorByName(Menus.LOGIN))).createUserErrorChecker(command.toString());
        GraphicalUserInterface.returnGraphicalResponse(response);
    }
}
