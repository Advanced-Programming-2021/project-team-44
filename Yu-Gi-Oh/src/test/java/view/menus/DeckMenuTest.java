package view.menus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckMenuTest {

    @Test
    void commandHandlerTest() {
        DeckMenu deckMenu = new DeckMenu(new MainMenu(new LoginMenu()));
        Assertions.assertEquals(deckMenu.commandHandler("menu exittt")[0], "-1");
        Assertions.assertEquals(deckMenu.commandHandler("menu enter")[0], "0");
        Assertions.assertEquals(deckMenu.commandHandler("menu exit")[0], "1");
        Assertions.assertEquals(deckMenu.commandHandler("menu show-current")[0], "2");
        Assertions.assertEquals(deckMenu.commandHandler("deck create dn")[0], "3");
        Assertions.assertEquals(deckMenu.commandHandler("deck create dn")[1], "dn");
        Assertions.assertEquals(deckMenu.commandHandler("deck delete dn")[0], "4");
        Assertions.assertEquals(deckMenu.commandHandler("deck delete dn")[1], "dn");
        Assertions.assertEquals(deckMenu.commandHandler("deck set-activate dn")[0], "5");
        Assertions.assertEquals(deckMenu.commandHandler("deck set-activate dn")[1], "dn");
        Assertions.assertEquals(deckMenu.commandHandler("deck add-card --card cn --deck dn")[0], "6");
        Assertions.assertEquals(deckMenu.commandHandler("deck add-card --card cn --deck dn")[1], "--card cn --deck dn");
        Assertions.assertEquals(deckMenu.commandHandler("deck rm-card --card cn --deck dn")[0], "7");
        Assertions.assertEquals(deckMenu.commandHandler("deck rm-card --card cn --deck dn")[1], "--card cn --deck dn");
        Assertions.assertEquals(deckMenu.commandHandler("deck show --all")[0], "8");
        Assertions.assertEquals(deckMenu.commandHandler("deck show --cards")[0], "9");
        Assertions.assertEquals(deckMenu.commandHandler("deck show --deck-name dn --side")[0], "10");
        Assertions.assertEquals(deckMenu.commandHandler("deck show --deck-name dn --side")[1], "--deck-name dn --side");
        Assertions.assertEquals(deckMenu.commandHandler("card show cn")[0], "11");
        Assertions.assertEquals(deckMenu.commandHandler("card show cn")[1], "cn");
        Assertions.assertEquals(deckMenu.commandHandler("help")[0], "99");

    }
}