module graphics {
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;
    requires java.desktop;

    opens graphics to javafx.fxml;
    opens graphics.login_menu_subPages to javafx.fxml;
    opens graphics.main_menu_subPages to javafx.fxml;

    exports graphics;
    exports graphics.login_menu_subPages;
    exports graphics.main_menu_subPages;
}