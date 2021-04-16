package view.menus;

public class DeckMenu extends Menu {
    public DeckMenu(Menu parentMenu) {
        super(Menus.DECK, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public String[] commandHandler(String input) {
        return null;
    }
}
