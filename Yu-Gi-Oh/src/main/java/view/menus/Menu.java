package view.menus;

import controller.Core;
import controller.processors.Processor;
import view.UserInterface;

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

    //Initialization Block
    static {
        menus = new ArrayList<>();
    }

    public Menu(Menus name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
        menus.add(this);
    }

    public static Menu getMenuByName(Menus name) {
        for (Menu menu : menus)
            if (menu.name == name)
                return menu;
        return null;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public abstract String[] commandHandler(String input);

    public void setSubMenus(HashMap<Integer, Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public void execute() {
        UserInterface.returnResponse(Objects.requireNonNull(Processor.getProcessorByName(this.name)).showMenu());
        Menu nextMenu;
        String input = scanner.nextLine().trim();
        String[] commandId = commandHandler(input);
        String response = Core.menuDistributor(Integer.parseInt(commandId[0]), commandId[1]);
        nextMenu = Menu.getMenuByName(Core.currentMenu);
        UserInterface.returnResponse(response);
        Objects.requireNonNull(nextMenu).execute();
    }
}
