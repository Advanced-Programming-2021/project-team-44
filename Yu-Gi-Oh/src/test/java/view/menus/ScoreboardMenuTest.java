package view.menus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardMenuTest {

    @Test
    void commandHandlerTest() {
        ScoreboardMenu scoreboardMenu = new ScoreboardMenu(new MainMenu(new LoginMenu()));
        Assertions.assertEquals(scoreboardMenu.commandHandler("menu exittt")[0], "-1");
        Assertions.assertEquals(scoreboardMenu.commandHandler("menu enter")[0], "0");
        Assertions.assertEquals(scoreboardMenu.commandHandler("menu exit")[0], "1");
        Assertions.assertEquals(scoreboardMenu.commandHandler("menu show-current")[0], "2");
        Assertions.assertEquals(scoreboardMenu.commandHandler("scoreboard show")[0], "3");
        Assertions.assertEquals(scoreboardMenu.commandHandler("help")[0], "99");

    }
}