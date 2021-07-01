package view.menus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopMenuTest {

    @Test
    void commandHandlerTest() {
        ShopMenu shopMenu = new ShopMenu(new MainMenu(new LoginMenu()));
        Assertions.assertEquals(shopMenu.commandHandler("menu exittt")[0], "-1");
        Assertions.assertEquals(shopMenu.commandHandler("menu enter")[0], "0");
        Assertions.assertEquals(shopMenu.commandHandler("menu exit")[0], "1");
        Assertions.assertEquals(shopMenu.commandHandler("menu show-current")[0], "2");
        Assertions.assertEquals(shopMenu.commandHandler("shop buy cn")[0], "3");
        Assertions.assertEquals(shopMenu.commandHandler("shop buy cn")[1], "cn");
        Assertions.assertEquals(shopMenu.commandHandler("shop show --all")[0], "4");
        Assertions.assertEquals(shopMenu.commandHandler("card show cn")[0], "5");
        Assertions.assertEquals(shopMenu.commandHandler("card show cn")[1], "cn");
    }
}