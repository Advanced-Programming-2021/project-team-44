package view.menus;

public class DuelMenu extends Menu {
    public DuelMenu(Menu parentMenu) {
        super(Menus.DUEL, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public String[] commandHandler(String input) {
        return null;
    }
}
