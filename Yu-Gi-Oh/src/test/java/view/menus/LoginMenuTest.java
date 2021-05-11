package view.menus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginMenuTest {

    @Test
    void commandHandlerTest() {
        LoginMenu loginMenu = new LoginMenu();
        Assertions.assertEquals(loginMenu.commandHandler("menu exittt")[0], "-1");
        Assertions.assertEquals(loginMenu.commandHandler("menu enter")[0], "0");
        Assertions.assertEquals(loginMenu.commandHandler("menu exit")[0], "1");
        Assertions.assertEquals(loginMenu.commandHandler("menu show-current")[0], "2");
        Assertions.assertEquals(loginMenu.commandHandler("user create --username un --nickname nn --password pw")[0], "3");
        Assertions.assertEquals(loginMenu.commandHandler("user create --username un --nickname nn --password pw")[1], "--username un --nickname nn --password pw");
        Assertions.assertEquals(loginMenu.commandHandler("user login --username un --nickname nn --password pw")[0], "4");
        Assertions.assertEquals(loginMenu.commandHandler("user login --username un --nickname nn --password pw")[1], "--username un --nickname nn --password pw");

    }
}