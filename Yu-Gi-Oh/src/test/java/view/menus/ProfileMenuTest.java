package view.menus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileMenuTest {

    @Test
    void commandHandlerTest() {
        ProfileMenu profileMenu = new ProfileMenu(new MainMenu(new LoginMenu()));
        Assertions.assertEquals(profileMenu.commandHandler("menu exittt")[0], "-1");
        Assertions.assertEquals(profileMenu.commandHandler("menu enter")[0], "0");
        Assertions.assertEquals(profileMenu.commandHandler("menu exit")[0], "1");
        Assertions.assertEquals(profileMenu.commandHandler("menu show-current")[0], "2");
        Assertions.assertEquals(profileMenu.commandHandler("profile change --nickname nn")[0], "3");
        Assertions.assertEquals(profileMenu.commandHandler("profile change --nickname nn")[1], "--nickname nn");
        Assertions.assertEquals(profileMenu.commandHandler("profile change --password --current cpw --new pw")[0], "4");
        Assertions.assertEquals(profileMenu.commandHandler("profile change --password --current cpw --new pw")[1], "--current cpw --new pw");
        Assertions.assertEquals(profileMenu.commandHandler("show profile")[0], "5");

    }
}