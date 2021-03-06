package controller.processors;

import controller.Core;
import models.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.menus.Menus;

import static org.junit.jupiter.api.Assertions.*;

class ProfileMenuProcessorTest {

    @BeforeEach
    void accountsClear(){
        Account.accounts.clear();
    }

    @Test
    void commandDistributorTest() {
        Core.cardInitializer();
        ProfileMenuProcessor profileMenuProcessor = new ProfileMenuProcessor();
        Processor.loggedInUser = new Account("matinKing","12345","matadysa");
        Account account = new Account("matinKings","12345","matadysaaa");
        String responseFor0;
        String responseFor1;
        String responseFor2;
        String responseFor3;
        String responseFor4;
        String responseFor5;
        String responseFor99;

        responseFor0 = "menu navigation is not possible";
        Assertions.assertEquals(profileMenuProcessor.process(0,"any menu"), responseFor0);


        responseFor1 = "";
        Assertions.assertEquals(profileMenuProcessor.process(1,"any menu"), responseFor1);
        Assertions.assertEquals(Core.currentMenu, Menus.MAIN);


        responseFor2 = "Profile Menu\n";
        Assertions.assertEquals(profileMenuProcessor.process(2,""), responseFor2);


        responseFor3 = "invalid command";
        Assertions.assertEquals(profileMenuProcessor.process(4,"--current 12313 -c 2131314 -n 23123"), responseFor3);
        responseFor3 = "invalid command";
        Assertions.assertEquals(profileMenuProcessor.process(4,"-c 12313 -n 2131314 --new 23123"), responseFor3);
        responseFor3 = "invalid command";
        Assertions.assertEquals(profileMenuProcessor.process(4,"-p 12313 --current 2131314 -n 23123"), responseFor3);
        responseFor3 = "invalid command";
        Assertions.assertEquals(profileMenuProcessor.process(4,""), responseFor3);
        responseFor3 = "invalid current Password";
        Assertions.assertEquals(profileMenuProcessor.process(4,"-n 1234 -c @4( )_$"), responseFor3);
        responseFor3 = "invalid new Password";
        Assertions.assertEquals(profileMenuProcessor.process(4,"-c 1234 -n @4( )_$"), responseFor3);
        responseFor3 = "current password is invalid";
        Assertions.assertEquals(profileMenuProcessor.process(4,"-c 123456 -n @4()_$"), responseFor3);
        responseFor3 = "please enter a new password";
        Assertions.assertEquals(profileMenuProcessor.process(4,"-c 12345 -n 12345"), responseFor3);
        responseFor3 = "password changed successfully!";
        Assertions.assertEquals(profileMenuProcessor.process(4,"-c 12345 -n 1234567m"), responseFor3);
        Assertions.assertEquals(Processor.loggedInUser.getPassword(), "1234567m");


        responseFor4 = "invalid command";
        Assertions.assertEquals(profileMenuProcessor.process(3,"--nickname Rostam -n asadi"), responseFor4);
        responseFor4 = "invalid command";
        Assertions.assertEquals(profileMenuProcessor.process(3,"--nickname Rostam -p asadi"), responseFor4);
        responseFor4 = "invalid command";
        Assertions.assertEquals(profileMenuProcessor.process(3,""), responseFor4);
        responseFor4 = "invalid Nickname";
        Assertions.assertEquals(profileMenuProcessor.process(3,"-n www kuche.com"), responseFor4);
        responseFor4 = "user with nickname matadysaaa already exists";
        Assertions.assertEquals(profileMenuProcessor.process(3,"-n matadysaaa"), responseFor4);
        responseFor4 = "nickname changed successfully!";
        Assertions.assertEquals(profileMenuProcessor.process(3,"-n KingFokingMatin"), responseFor4);
        Assertions.assertEquals(Processor.loggedInUser.getNickname(), "KingFokingMatin");

        Processor.loggedInUser = account;
        responseFor5 = """
                ----------------------------------------
                Nickname: matadysaaa
                Username: matinKings
                Score: 0 pts
                Coin: 50000
                ----------------------------------------""";
        Assertions.assertEquals(profileMenuProcessor.process(5,""), responseFor5);

        responseFor99 ="""
                * Commands in this Menu:
                menu enter <name>
                menu exit
                menu show-current
                show profile
                profile change <new nickname>
                profile change --password <old password> <new password>
                help
                """;
        Assertions.assertEquals(profileMenuProcessor.process(99, ""), responseFor99);

        profileMenuProcessor.enterMenu(Menus.MAIN);
        Processor.getProcessorByName(Menus.MAIN);
        Processor.getProcessorByName(Menus.PROFILE);


    }

}