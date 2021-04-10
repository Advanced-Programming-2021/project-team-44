package View.Menus;

public class DuelMenu extends Menu {
    public DuelMenu(Menu parentMenu) {
        super(Menus.DUEL, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public int commandHandler(String input) {
        return -1;
    }
}
