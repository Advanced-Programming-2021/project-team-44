package view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GraphicsView extends Application implements Illustratable{
    public static Stage stage;
    private Parent pane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GraphicsView.stage = stage;
        pane = constr
    }


    @Override
    public Parent constructPane() {
        return null;
    }
}
