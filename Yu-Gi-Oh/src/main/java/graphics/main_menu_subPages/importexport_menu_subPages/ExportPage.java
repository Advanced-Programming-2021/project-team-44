package graphics.main_menu_subPages.importexport_menu_subPages;

import controller.processors.ImportExportMenuProcessor;
import graphics.GraphicalUserInterface;
import graphics.main_menu_subPages.ImportExportMenuPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class ExportPage extends Application {
    private static Stage stage;
    private static Pane pane;
    public TextField cardNameField;
    public TextField pathField;

    @Override
    public void start(Stage stage) throws Exception {
        ExportPage.stage = stage;
        URL pageUrl = Objects.requireNonNull(getClass().getResource("/static/fxml/main_menu_page_subPages/importexport_menu_page_subPages/ExportPage.fxml"));
        pane = FXMLLoader.load(pageUrl);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void redirectToImportExportMenu() {
        try {
            (new ImportExportMenuPage()).start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportHandler(MouseEvent mouseEvent) {
        if (pathField.getText() == null) pathField.setText("");
        String response = ImportExportMenuProcessor.getInstance().exportCardErrorChecker(cardNameField.getText(), pathField.getText());
        GraphicalUserInterface.returnGraphicalResponse(response);
    }
}
