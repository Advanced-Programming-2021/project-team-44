module graphics {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens graphics.view to javafx.fxml;
    opens graphics.controller to javafx.fxml;
    exports graphics.view;
    exports graphics.controller;
}