package controller.processors;

import controller.Core;
import models.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.menus.Menus;

import static org.junit.jupiter.api.Assertions.*;

class ImportExportMenuProcessorTest {

    @BeforeEach
    void accountsClear(){
        Account.accounts.clear();
    }

    @Test
    void commandDistributor() {
        Core.cardInitializer();
        ImportExportMenuProcessor importExportMenuProcessor = new ImportExportMenuProcessor();
        Account account = new Account("matinKing","12345","matadysa");

        String responseFor0 = "menu navigation is not possible";
        Assertions.assertEquals(importExportMenuProcessor.process(0,"any menu"), responseFor0);

        String responseFor1 = "";
        Assertions.assertEquals(importExportMenuProcessor.process(1,"any menu"), responseFor1);
        Assertions.assertEquals(Core.currentMenu, Menus.MAIN);

        String responseFor2 = "Import/Export Menu\n";
        Assertions.assertEquals(importExportMenuProcessor.process(2,""), responseFor2);

        String responseFor4 = "Card exported successfully!";
        Assertions.assertEquals(importExportMenuProcessor.process(4,"Axe Raider"), responseFor4);

        String responseFor99 = "* Commands in this Menu:\n" +
                "menu enter <name>\n" +
                "menu exit\n" +
                "menu show-current\n" +
                "import card <name>\n" +
                "export card <name>\n" +
                "help\n";
        Assertions.assertEquals(importExportMenuProcessor.process(99,""), responseFor99);
    }

}