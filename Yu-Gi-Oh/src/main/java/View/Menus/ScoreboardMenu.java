package View.Menus;

public class ScoreboardMenu extends Menu {//0
    public ScoreboardMenu(Menu parentMenu) {
        super(Menus.SCOREBOARD, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public int commandHandler(String input) {
        return -1;
    }
}
