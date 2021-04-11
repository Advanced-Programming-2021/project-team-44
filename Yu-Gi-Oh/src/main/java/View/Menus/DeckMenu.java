package View.Menus;

public class DeckMenu extends Menu {
    public DeckMenu(Menu parentMenu) {
        super(Menus.DECK, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public int commandHandler(String input) {
        return -1;
    }
}
