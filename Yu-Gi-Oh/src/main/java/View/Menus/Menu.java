package View.Menus;

import Controller.Core;
import View.UserInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public abstract class Menu {//0
    protected Menus name;
    protected Menu parentMenu;
    protected HashMap<Integer, Menu> subMenus;
    protected static Scanner scanner;
    public static ArrayList<Menu> menus;

    public Menu(Menus name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
        menus.add(this);
    }

    public abstract int commandHandler(String input);

    public static Menu getMenuByName(Menus name) {
        for (Menu menu : menus)
            if (menu.name == name)
                return menu;
        return null;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public void setSubMenus(HashMap<Integer, Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public void execute() {
        Menu nextMenu;
        String input = scanner.nextLine();
        int commandId = commandHandler(input);
        String response = Core.menuDistributor(commandId);
        nextMenu = Menu.getMenuByName(Core.currentMenu);
        UserInterface.returnResponse(response);
        Objects.requireNonNull(nextMenu).execute();
    }
}
