package view;

import controller.processors.LoginMenuProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import view.menus.LoginMenu;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {


    @Test
    void returnResponse() {
        UserInterface.returnResponse("ali");
    }
}