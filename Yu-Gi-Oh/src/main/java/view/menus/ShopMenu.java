package view.menus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopMenu extends Menu { //DONE

    public ShopMenu(Menu parentMenu) {
        super(Menus.SHOP, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public String[] commandHandler(String input) {
        String[] output = {"-1", ""};
        Pattern pattern = Pattern.compile("^(help|menu enter|menu exit|menu show-current|shop buy|shop show --all|increase --money|card show)\\b(?:\\s+(.*))?$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            switch (matcher.group(1)) {
                case "menu enter" -> output[0] = "0";
                case "menu exit" -> output[0] = "1";
                case "menu show-current" -> output[0] = "2";
                case "shop buy" -> output[0] = "3";
                case "shop show --all" -> output[0] = "4";
                case "card show" -> output[0] = "5";
                case "increase --money" -> output[0] = "6";
                case "help" -> output[0] = "99";
            }
            output[1] = matcher.group(2);
            if (output[1] == null) output[1] = "";
        }
        return output;
    }
}
