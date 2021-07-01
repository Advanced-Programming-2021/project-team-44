package controller.processors;

import controller.Core;
import models.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginMenuProcessorTest {

    @Test
    void commandDistributorTest() {
        Core.Initializer();
        LoginMenuProcessor loginMenuProcessor = new LoginMenuProcessor();
        Account account = new Account("matinKing","12345","matadysa");
        String responseFor0;
        String responseFor1;
        String responseFor2;
        String responseFor3;
        String responseFor4;

        responseFor0 = "please login first";
        Assertions.assertEquals(loginMenuProcessor.process(0, "Main menu"), responseFor0);


        //responseFor1 = "";


        responseFor2 = "Login Menu";
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

    }

}