package view.menus;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu extends Menu { //DONE

    public MainMenu(Menu parentMenu) {
        super(Menus.MAIN, parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(0, new DuelMenu(this));
        submenus.put(1, new DeckMenu(this));
        submenus.put(2, new ScoreboardMenu(this));
        submenus.put(3, new ProfileMenu(this));
        submenus.put(4, new ShopMenu(this));
        submenus.put(5, new ImportExportMenu(this));
        this.setSubMenus(submenus);
    }

    @Override
    public String[] commandHandler(String input) {
        String[] output = {"-1", ""};
        Pattern pattern = Pattern.compile("^(help|menu enter|menu exit|menu show-current|user logout|duel --new --ai|duel --new)\\b(?:\\s+(.*))?$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            switch (matcher.group(1)) {
                case "menu enter" -> output[0] = "0";
                case "menu exit" -> output[0] = "1";
                case "menu show-current" -> output[0] = "2";
                case "user logout" -> output[0] = "3";
                case "duel --new" -> output[0] = "4";
                case "duel --new --ai" -> output[0] = "5";
                case "help" -> output[0] = "99";
            }
            output[1] = matcher.group(2);
            if (output[1] == null) output[1] = "";
        }
        return output;
    }
}
