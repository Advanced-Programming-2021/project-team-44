package view.menus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImportExportMenuTest {

    @Test
    void commandHandlerTest() {
        ImportExportMenu importExportMenu = new ImportExportMenu(new MainMenu(new LoginMenu()));
        Assertions.assertEquals(importExportMenu.commandHandler("menu exittt")[0], "-1");
        Assertions.assertEquals(importExportMenu.commandHandler("menu enter")[0], "0");
        Assertions.assertEquals(importExportMenu.commandHandler("menu exit")[0], "1");
        Assertions.assertEquals(importExportMenu.commandHandler("menu show-current")[0], "2");
        Assertions.assertEquals(importExportMenu.commandHandler("import card cn")[0], "3");
        Assertions.assertEquals(importExportMenu.commandHandler("import card cn")[1], "cn");
        Assertions.assertEquals(importExportMenu.commandHandler("export card cn")[0], "4");
        Assertions.assertEquals(importExportMenu.commandHandler("export card cn")[1], "cn");
        Assertions.assertEquals(importExportMenu.commandHandler("help")[0], "99");

    }
}