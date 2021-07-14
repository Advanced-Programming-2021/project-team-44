package graphics.main_menu_subPages;

import graphics.MainMenuPage;
import javafx.stage.Stage;

public interface MainMenuNavigation {
    default void redirectToMainMenu(Stage stage) {
        try {
            (new MainMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void redirectToDuelPage(Stage stage) {
        try {
            (new DuelPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void redirectToDeckMenu(Stage stage) {
        try {
            (new DeckMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void redirectToProfileMenu(Stage stage) {
        try {
            (new ProfileMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void redirectToScoreboardMenu(Stage stage) {
        try {
            (new ScoreboardMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void redirectToShopMenu(Stage stage) {
        try {
            (new ShopMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    default void redirectToImportExportMenu(Stage stage) {
        try {
            (new ImportExportMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
