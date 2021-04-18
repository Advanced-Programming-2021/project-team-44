package view.menus;

public class ImportExportMenu extends Menu {//0
    public ImportExportMenu(Menu parentMenu) {
        super(Menus.IMPORTEXPORT, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public String[] commandHandler(String input) {
        return null;
    }
}
