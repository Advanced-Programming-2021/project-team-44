package view.menus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoreboardMenu extends Menu { //DONE

    public ScoreboardMenu(Menu parentMenu) {
        super(Menus.SCOREBOARD, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public String[] commandHandler(String input) {
        String[] output = {"-1", ""};
        Pattern pattern = Pattern.compile("^(menu enter|menu exit|menu show-current|scoreboard show)\\b(?:\\s+(.*))?$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            switch (matcher.group(1)) {
                case "menu enter" -> output[0] = "0";
                case "menu exit" -> output[0] = "1";
                case "menu show-current" -> output[0] = "2";
                case "scoreboard show" -> output[0] = "3";
            }
            output[1] = matcher.group(2);
            if (output[1] == null) output[1] = "";
        }
        return output;
    }
}
