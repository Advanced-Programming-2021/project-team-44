package view.menus;

public class ShopMenu extends Menu {//0
    public ShopMenu(Menu parentMenu) {
        super(Menus.SHOP, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public String[] commandHandler(String input) {
        return null;
    }
}
