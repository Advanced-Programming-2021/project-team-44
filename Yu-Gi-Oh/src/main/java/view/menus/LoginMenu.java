package view.menus;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu extends Menu {

    public LoginMenu() {
        super(Menus.LOGIN, null);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(0, new MainMenu(this));
        this.setSubMenus(submenus);
    }

    @Override
    public String[] commandHandler(String input) {
        String[] output = {"-1", ""};
        Pattern pattern = Pattern.compile("^(menu enter|menu exit|menu show-current|user create|user login)\\b(?:\\s+(.*))?$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            switch (matcher.group(1)) {
                case "menu enter" -> output[0] = "0";
                case "menu exit" -> output[0] = "1";
                case "menu show-current" -> output[0] = "2";
                case "user create" -> output[0] = "3";
                case "user login" -> output[0] = "4";
            }
            output[1] = matcher.group(2);
        }
        return output;
    }
}
