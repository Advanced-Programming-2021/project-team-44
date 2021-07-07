module graphics {
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.gson;
    requires java.desktop;

    opens graphics to javafx.fxml;
    opens graphics.login_menu_subPages to javafx.fxml;
    opens graphics.main_menu_subPages to javafx.fxml;
    opens graphics.main_menu_subPages.importexport_menu_subPages to javafx.fxml;

    opens models to com.google.gson;
    opens models.cards to com.google.gson;
    opens models.duel_models to com.google.gson;

    exports graphics;
    exports graphics.login_menu_subPages;
    exports graphics.main_menu_subPages;
    exports graphics.main_menu_subPages.importexport_menu_subPages;

    exports models;
    exports models.cards;
    exports models.duel_models;
}