package controller.processors;

import view.menus.Menus;

public class ProfileMenuProcessor extends Processor {
    public ProfileMenuProcessor() {
        super(Menus.PROFILE);
    }

    //Error Checker
    private String changeNicknameErrorChecker(String input) {
        return null;
    }

    private String changePasswordErrorChecker(String input) {
        return null;
    }

    //Command Performer
    private void changeNickname(String newNickname) {
        //Todo
    }

    private void changePassword(String newPassword) {
    }

    private String showProfile() {
        return null;
    } //Option

    @Override
    public String commandDistributor(int commandId, String commandArguments) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> {

            }
            case 1 -> {

            }
            case 2 -> {

            }
            case 3 -> {

            }
            case 4 -> {

            }
        }
        return response;
    }

    @Override
    protected String enterMenuErrorChecker(String input) {
        return null;
    }

    @Override
    protected void enterMenu(Menus menu) {

    }

    @Override
    protected void exitMenu() {

    }
}
