package view.menus;

public class ProfileMenu extends Menu {//0
    public ProfileMenu(Menu parentMenu) {
        super(Menus.PROFILE, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public String[] commandHandler(String input) {
        return null;
    }
}
