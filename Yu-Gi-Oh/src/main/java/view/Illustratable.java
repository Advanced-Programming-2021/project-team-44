package view;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public interface Illustratable {
    Integer[] windowDimensions = {1920, 1080};

    Parent constructPane();

    default Pane setDimensions(Pane pane) {
        pane.setPrefWidth(windowDimensions[0]);
        pane.setPrefHeight(windowDimensions[1]);
        return pane;
    }
}
