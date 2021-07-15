package controller.processors;

import controller.Core;
import models.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import view.menus.Menus;

import static org.junit.jupiter.api.Assertions.*;

class LoginMenuProcessorTest {

    @Test
    void commandDistributorTest() {
        Core.cardInitializer();
        LoginMenuProcessor loginMenuProcessor = new LoginMenuProcessor();
        Account account = new Account("matinKing","12345","matadysa");
        String responseFor0;
        String responseFor1;
        String responseFor2;
        String responseFor3;
        String responseFor4;
        String responseFor99;

        responseFor0 = "please login first";
        Assertions.assertEquals(loginMenuProcessor.process(0, "Main menu"), responseFor0);


        //responseFor1 = "";


        responseFor2 = "Login Menu\n";
        Assertions.assertEquals(loginMenuProcessor.process(2, ""), responseFor2);


        responseFor3 = "invalid command";
        Assertions.assertEquals(loginMenuProcessor.process(3, "-u abas --password 246810 -n karim --username abas"), responseFor3);
        responseFor3 = "invalid command";
        Assertions.assertEquals(loginMenuProcessor.process(3, "-n abas --password 246810 -n karim --username abas"), responseFor3);
        responseFor3 = "invalid command";
        Assertions.assertEquals(loginMenuProcessor.process(3, "-p abas --password 246810 -n karim --username abas"), responseFor3);
        responseFor3 = "invalid command";
        Assertions.assertEquals(loginMenuProcessor.process(3, "-u abas  -t karim --username abas"), responseFor3);
        responseFor3 = "invalid command";
        Assertions.assertEquals(loginMenuProcessor.process(3, "-u abas --password 246810 "), responseFor3);
        responseFor3 = "invalid command";
        Assertions.assertEquals(loginMenuProcessor.process(3, "-u abas --password 246810 "), responseFor3);
        responseFor3 = "invalid username";
        Assertions.assertEquals(loginMenuProcessor.process(3, "-u abas bu azar --password 246810 --nickname karim"), responseFor3);
        responseFor3 = "invalid nickname";
        Assertions.assertEquals(loginMenuProcessor.process(3, "-u abas --password 246810 -n karim bagheri"), responseFor3);
        responseFor3 = "invalid password";
        Assertions.assertEquals(loginMenuProcessor.process(3, "-u abas --password 246810 wqe -n karim"), responseFor3);
        responseFor3 = "user with username matinKing already exists";
        Assertions.assertEquals(loginMenuProcessor.process(3, "-u matinKing --password 246810 -n karim"), responseFor3);
        responseFor3 = "user with nickname matadysa already exists";
        Assertions.assertEquals(loginMenuProcessor.process(3, "-u matinKing2 --password 246810 -n matadysa"), responseFor3);
        responseFor3 = "user created successfully!";
        Assertions.assertEquals(loginMenuProcessor.process(3, "-u matinKing2 --password 246810 -n matadysa2"), responseFor3);
        Assertions.assertNotNull(Account.getAccountByUsername("matinKing2"));
        Assertions.assertNotNull(Account.getAccountByNickname("matadysa2"));

        responseFor4 = "invalid command";
        Assertions.assertEquals(loginMenuProcessor.process(4, "-u abas --password 246810 -n karim --username abas"), responseFor4);
        responseFor4 = "invalid command";
        Assertions.assertEquals(loginMenuProcessor.process(4, "-n abas --password 246810 -n karim --username abas"), responseFor4);
        responseFor4 = "invalid command";
        Assertions.assertEquals(loginMenuProcessor.process(4, "-p abas --password 246810 -n karim --username abas"), responseFor4);
        responseFor4 = "invalid command";
        Assertions.assertEquals(loginMenuProcessor.process(4, "-u abas  -t karim --username abas"), responseFor4);
        responseFor4 = "Username and password did not match!";
        Assertions.assertEquals(loginMenuProcessor.process(4, "-u abas --password 246810 "), responseFor4);
        Account account1 = new Account("ali","1234","alibaba");
        responseFor4 = "Username and password did not match!";
        Assertions.assertEquals(loginMenuProcessor.process(4, "-u ali --password 246810 "), responseFor4);
        responseFor4 = "user logged in successfully!";
        Assertions.assertEquals(loginMenuProcessor.process(4, "-u ali --password 1234 "), responseFor4);

        responseFor99 = """
                * Commands in this Menu:
                menu enter <name>
                exit
                menu show-current
                user create <username> <nickname> <password>
                user login <username> <password>
                help
                """;
        Assertions.assertEquals(loginMenuProcessor.process(99, ""), responseFor99);

        loginMenuProcessor.enterMenu(Menus.MAIN);

    }


}