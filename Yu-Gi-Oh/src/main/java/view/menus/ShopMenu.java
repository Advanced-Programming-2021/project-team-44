package view.menus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopMenu extends Menu {//0
    public ShopMenu(Menu parentMenu) {
        super(Menus.SHOP, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public String[] commandHandler(String input) {
        String[] output = {"-1", ""};
        Pattern pattern = Pattern.compile("^(menu enter|menu exit|menu show-current|shop buy|shop show --all|card show)\\b(?:\\s+(.*))?$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            switch (matcher.group(1)) {
                case "menu enter" -> output[0] = "0";
                case "menu exit" -> output[0] = "1";
                case "menu show-current" -> output[0] = "2";
                case "shop buy" -> output[0] = "3";
                case "shop show --all" -> output[0] = "4";
                case "card show" -> output[0] = "5";
            }
            output[1] = matcher.group(2);
        }
        return output;
    }
}
