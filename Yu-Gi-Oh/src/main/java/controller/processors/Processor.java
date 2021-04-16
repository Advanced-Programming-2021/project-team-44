package controller.processors;

import view.menus.Menus;

import java.util.ArrayList;

public abstract class Processor {
    protected Menus name;
    public static ArrayList<Processor> processors;

    static {
        processors = new ArrayList<>();
    }

    public Processor(Menus name) {
        this.name = name;
        processors.add(this);
    }

    public static Processor getProcessorByName(Menus name) {
        for (Processor processor : processors)
            if (processor.name == name)
                return processor;
        return null;
    }

    public abstract String commandDistributor(int commandId);

    //Error Checker
    protected abstract String enterMenuErrorChecker(String input);

    //Command Performer
    protected abstract void enterMenu(Menus menu);

    protected abstract void exitMenu();

    protected String showMenu()  {
        return this.name.toBePrintedName;
    }
}
