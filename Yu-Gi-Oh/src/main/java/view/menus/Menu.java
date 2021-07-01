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
    private boolean firstTime;

    //Initialization Block
    static {
        menus = new ArrayList<>();
    }

    public Menu(Menus name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
        menus.add(this);
        this.firstTime = true;
    }

    public static Menu getMenuByName(Menus name) {
        if (name == Menus.PLAYER_DUEL || name == Menus.AI_DUEL)
            return getMenuByName(Menus.DUEL);
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
        if (firstTime) {
            UserInterface.returnResponse("\n" + Objects.requireNonNull(Processor.getProcessorByName(this.name)).showMenu());
            firstTime = false;
        }
        Menu nextMenu;
        System.out.print("$ ");
        String input = scanner.nextLine().trim();
        String[] commandId = commandHandler(input);
        String response = Core.menuDistributor(Integer.parseInt(commandId[0]), commandId[1]);
        nextMenu = Objects.requireNonNull(Menu.getMenuByName(Core.currentMenu));
        if (nextMenu != this) firstTime = true;
        UserInterface.returnResponse(response);
        nextMenu.execute();
    }
}
