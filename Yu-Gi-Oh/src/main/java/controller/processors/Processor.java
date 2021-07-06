package controller.processors;

import models.Account;
import models.Menus;

import java.util.ArrayList;

public abstract class Processor {
    public static Account loggedInUser;
    public static ArrayList<Processor> processors;

    static {
        loggedInUser = null;
        processors = new ArrayList<>();
    }

    protected Menus name;

    protected Processor(Menus name) {
        this.name = name;
        processors.add(this);
    }

    public static Processor getProcessorByName(Menus name) {
        for (Processor processor : processors)
            if (processor.name == name)
                return processor;
        return null;
    }

    //Error Checker
    public abstract String enterMenuErrorChecker(String input);

    //Command Performer
    public abstract String help();

    public abstract void enterMenu(Menus menu);

    public abstract void exitMenu();

    public String showMenu() {
        return this.name.toBePrintedName + "\n";
    }
}
