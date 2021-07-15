package view.menus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {

    @Test
    void commandHandlerTest() {
        MainMenu mainMenu = new MainMenu(new LoginMenu());
        Assertions.assertEquals(mainMenu.commandHandler("menu exittt")[0], "-1");
        Assertions.assertEquals(mainMenu.commandHandler("menu enter")[0], "0");
        Assertions.assertEquals(mainMenu.commandHandler("menu exit")[0], "1");
        Assertions.assertEquals(mainMenu.commandHandler("menu show-current")[0], "2");
        Assertions.assertEquals(mainMenu.commandHandler("user logout")[0], "3");
        Assertions.assertEquals(mainMenu.commandHandler("duel --new --second-player p2n --rounds 1")[0], "4");
        Assertions.assertEquals(mainMenu.commandHandler("duel --new --second-player p2n --rounds 1")[1], "--second-player p2n --rounds 1");
        Assertions.assertEquals(mainMenu.commandHandler("duel --new --ai --rounds 3")[0], "5");
        Assertions.assertEquals(mainMenu.commandHandler("duel --new --ai --rounds 3")[1], "--rounds 3");
        Assertions.assertEquals(mainMenu.commandHandler("help")[0], "99");

    }
}