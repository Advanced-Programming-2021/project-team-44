package view.menus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeckMenu extends Menu { //DONE

    public DeckMenu(Menu parentMenu) {
        super(Menus.DECK, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public String[] commandHandler(String input) {
        String[] output = {"-1", ""};
        Pattern pattern = Pattern.compile("^(menu enter|" +
                "menu exit|" +
                "menu show-current|" +
                "deck create|" +
                "deck delete|" +
                "deck set activate|" +
                "deck add-card|" +
                "deck rm-card|" +
                "deck show --all|" +
                "deck show --cards|" +
                "deck show|" +
                "card show)\\b(?:\\s+(.*))?$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            switch (matcher.group(1)) {
                case "menu enter" -> output[0] = "0";
                case "menu exit" -> output[0] = "1";
                case "menu show-current" -> output[0] = "2";
                case "deck create" -> output[0] = "3";
                case "deck delete" -> output[0] = "4";
                case "deck set activate" -> output[0] = "5";
                case "deck add-card" -> output[0] = "6";
                case "deck rm-card" -> output[0] = "7";
                case "deck show --all" -> output[0] = "8";
                case "deck show --cards" -> output[0] = "9";
                case "deck show" -> output[0] = "10";
                case "card show" -> output[0] = "11";

            }
            output[1] = matcher.group(2);
        }
        return output;
    }
}
