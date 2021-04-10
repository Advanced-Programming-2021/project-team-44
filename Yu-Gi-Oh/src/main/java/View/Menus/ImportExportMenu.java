package View.Menus;

public class ImportExportMenu extends Menu {
    public ImportExportMenu(Menu parentMenu) {
        super(Menus.IMPORTEXPORT, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public int commandHandler(String input) {
        return -1;
    }
}
