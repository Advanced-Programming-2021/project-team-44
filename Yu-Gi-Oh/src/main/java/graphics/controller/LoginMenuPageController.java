package graphics.controller;

import graphics.view.StartPage;
import graphics.view.login_menu_subPages.CreditsPage;
import graphics.view.login_menu_subPages.LoginPage;
import graphics.view.login_menu_subPages.RegisterPage;
import javafx.scene.input.MouseEvent;

public class LoginMenuPageController {
    public void registerHandler(MouseEvent mouseEvent) {
        try {
            (new RegisterPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginHandler(MouseEvent mouseEvent) {
        try {
            (new LoginPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitHandler(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void creditsHandler(MouseEvent mouseEvent) {
        try {
            (new CreditsPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
