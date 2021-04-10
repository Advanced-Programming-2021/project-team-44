package View.Menus;

public class ShopMenu extends Menu {
    public ShopMenu(Menu parentMenu) {
        super(Menus.SHOP, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public int commandHandler(String input) {
        return -1;
    }
}
