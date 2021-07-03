package graphics.view.login_menu_subPages;

import graphics.view.LoginMenuPage;
import graphics.view.StartPage;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class CreditsPage extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        CreditsPage.stage = stage;
        URL pageUrl = getClass().getResource("/static/fxml/login_menu_page_subPages/CreditsPage.fxml");
        Parent pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        {
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    redirectToLoginMenu(keyEvent);
                }
            });
        }
        stage.setScene(scene);
        stage.show();
    }

    public void redirectToLoginMenu(KeyEvent event) {
        try {
            (new LoginMenuPage()).start(StartPage.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openProjectGithubRepository(MouseEvent mouseEvent) {
        System.out.println("bitch");
//        getHostServices().showDocument("https://github.com/Advanced-Programming-2021/project-team-44");
//        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
//            try {
//                Desktop.getDesktop().browse(new URI("https://github.com/Advanced-Programming-2021/project-team-44"));
//            } catch (IOException | URISyntaxException e) {
//                e.printStackTrace();
//            }
//        }
        String url = "https://github.com/Advanced-Programming-2021/project-team-44";
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
