package View.Menus;

public class ProfileMenu extends Menu {
    public ProfileMenu(Menu parentMenu) {
        super(Menus.PROFILE, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public int commandHandler(String input) {
        return -1;
    }
}
