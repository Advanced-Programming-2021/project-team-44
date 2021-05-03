package view.menus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportExportMenu extends Menu { //DONE

    public ImportExportMenu(Menu parentMenu) {
        super(Menus.IMPORTEXPORT, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public String[] commandHandler(String input) {
        String[] output = {"-1", ""};
        Pattern pattern = Pattern.compile("^(menu enter|menu exit|menu show-current|import card|export card)\\b(?:\\s+(.*))?$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            switch (matcher.group(1)) {
                case "menu enter" -> output[0] = "0";
                case "menu exit" -> output[0] = "1";
                case "menu show-current" -> output[0] = "2";
                case "import card" -> output[0] = "3";
                case "export card" -> output[0] = "4";
            }
            output[1] = matcher.group(2);
        }
        return output;
    }
}
