package view.menus;

public class ScoreboardMenu extends Menu {//0
    public ScoreboardMenu(Menu parentMenu) {
        super(Menus.SCOREBOARD, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public String[] commandHandler(String input) {
        return null;
    }
}
