package View.Menus;

import java.util.HashMap;

public class LoginMenu extends Menu {//0
    public LoginMenu() {
        super(Menus.LOGIN, null);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(0, new MainMenu(this));
        this.setSubMenus(submenus);
    } //0

    @Override
    public int commandHandler(String input) {
        return -1;
    }
}
