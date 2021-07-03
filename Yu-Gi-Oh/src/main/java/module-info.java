module graphics {
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;

    opens graphics to javafx.fxml;
    opens graphics.login_menu_subPages to javafx.fxml;

    exports graphics;
    exports graphics.login_menu_subPages;
}