package view.menus;

import controller.Core;
import controller.processors.DuelMenuProcessor;
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
        ((DuelMenuProcessor) Processor.getProcessorByName(Menus.DUEL)).execute();
        nextMenu = Objects.requireNonNull(Menu.getMenuByName(Core.currentMenu));
        nextMenu.execute();
    }
}
