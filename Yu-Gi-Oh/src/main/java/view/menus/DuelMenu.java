package view.menus;

import controller.Core;
import controller.processors.AIDuelMenuProcessor;
import controller.processors.PlayerDuelMenuProcessor;
import controller.processors.Processor;

import java.util.Objects;

public class DuelMenu extends Menu {
    public DuelMenu(Menu parentMenu) {
        super(Menus.DUEL, parentMenu);
        this.setSubMenus(null);
    }

    @Override
    public String[] commandHandler(String input) {
        return null;
    }

    @Override
    public void execute() {
        Menu nextMenu;
        //Duel Start
        if (Core.currentMenu == Menus.PLAYER_DUEL) ((PlayerDuelMenuProcessor) Objects.requireNonNull(Processor.getProcessorByName(Menus.PLAYER_DUEL))).execute();
        else ((AIDuelMenuProcessor) Objects.requireNonNull(Processor.getProcessorByName(Menus.AI_DUEL))).execute();
        //Duel End
        nextMenu = Objects.requireNonNull(Menu.getMenuByName(Core.currentMenu));
        nextMenu.execute();
    }
}
