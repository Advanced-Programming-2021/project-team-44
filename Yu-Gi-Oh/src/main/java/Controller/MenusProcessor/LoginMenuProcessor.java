package Controller.MenusProcessor;

public class LoginMenuProcessor {
    public String commandDistributor(int commandId) {
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

    //Command Performer
    private void exitMenu() {
        System.exit(0);
    } //done

    private void showMenu() {
        System.out.println("Login Menu");
    } //done

    private void createUser(String username, String password, String nickname) {
        //TODO
    }

    private void loginUser(String username, String password) {
        //TODO
    }

    //Error Checkers
    private void enterMenuErrorThrower() {
        //TODO
    }

    private void createUserErrorChecker(String input) {
        //TODO
        /*
        username
        password
        nickname

        if
        else if
        else {
        createUser(input)
        }
         */
    }

    private void loginUserErrorChecker(String input) {
        //TODO
    }
}
