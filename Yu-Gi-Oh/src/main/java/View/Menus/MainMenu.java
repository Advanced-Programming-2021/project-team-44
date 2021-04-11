package View.Menus;

import java.util.HashMap;

public class MainMenu extends Menu {
    public MainMenu(Menu parentMenu) {
        super(Menus.LOGIN, parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(0, new DuelMenu(this));
        submenus.put(1, new DeckMenu(this));
        submenus.put(2, new ScoreboardMenu(this));
        submenus.put(3, new ProfileMenu(this));
        submenus.put(4, new ShopMenu(this));
        submenus.put(5, new ImportExportMenu(this));
        this.setSubMenus(submenus);
    }

    @Override
    public int commandHandler(String input) {
        return -1;
    }
}
