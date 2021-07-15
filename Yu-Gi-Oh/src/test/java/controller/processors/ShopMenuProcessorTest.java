package controller.processors;

import com.sun.tools.javac.Main;
import controller.Core;
import models.Account;
import models.cards.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.menus.MainMenu;
import view.menus.Menus;

import static org.junit.jupiter.api.Assertions.*;

class ShopMenuProcessorTest {

    @BeforeEach
    void accountsClear(){
        Account.accounts.clear();
    }

    @Test
    void commandDistributorTest() {
        Core.cardInitializer();
        ShopMenuProcessor shopMenuProcessor = new ShopMenuProcessor();
        Account account = new Account("matinKing","12345","matadysa");
        Processor.loggedInUser = account;
        String responseFor0;
        String responseFor1;
        String responseFor2;
        String responseFor3;
        String responseFor4;
        String responseFor5;
        String responseFor6;
        String responseFor99;

        responseFor0 = "menu navigation is not possible";
        Assertions.assertEquals(shopMenuProcessor.process(0,"any menu"), responseFor0);

        responseFor1 = "";
        Assertions.assertEquals(shopMenuProcessor.process(1,"any menu"), responseFor1);
        Assertions.assertEquals(Core.currentMenu, Menus.MAIN);

        responseFor2 = "Shop Menu\n";
        Assertions.assertEquals(shopMenuProcessor.process(2,""), responseFor2);

        responseFor3 = "there is no card with this name";
        Assertions.assertEquals(shopMenuProcessor.process(3,"Not a card"), responseFor3);
        responseFor3 = "not enough money";
        account.increaseCoin(-50000);
        Assertions.assertEquals(shopMenuProcessor.process(3,"Axe Raider"), responseFor3);
        account.increaseCoin(10000);
        responseFor3 = "card bought successfully";
        Assertions.assertEquals(shopMenuProcessor.process(3,"Axe Raider"), responseFor3);
        Assertions.assertNotNull(account.getCardByName("Axe Raider"));

        responseFor4 = "Advanced Ritual Art:This card can be used to Ritual Summon any 1 Ritual Monster. You must also send Normal Monsters from your Deck to the Graveyard whose total Levels equal the Level of that Ritual Monster.\n" +
                "Alexandrite Dragon:Many of the czars' lost jewels can be found in the scales of this priceless dragon. Its creator remains a mystery, along with how they acquired the imperial treasures. But whosoever finds this dragon has hit the jackpot... whether they know it or not.\n" +
                "Axe Raider:An axe-wielding monster of tremendous strength and agility.\n" +
                "Baby dragon:Much more than just a child, this dragon is gifted with untapped power.\n" +
                "Battle OX:A monster with tremendous power, it destroys enemies with a swing of its axe.\n" +
                "Battle warrior:A warrior that fights with his bare hands!!!\n" +
                "Beast King Barbaros:You can Normal Summon/Set this card without Tributing, but its original ATK becomes 1900. You can Tribute 3 monsters to Tribute Summon (but not Set) this card. If Summoned this way: Destroy all cards your opponent controls.\n" +
                "Bitron:A new species found in electronic space. There's not much information on it.\n" +
                "Black Pendant:The equipped monster gains 500 ATK. When this card is sent from the field to the Graveyard: Inflict 500 damage to your opponent.\n" +
                "Blue-Eyes white dragon:This legendary dragon is a powerful engine of destruction. Virtually invincible, very few have faced this awesome creature and lived to tell the tale.\n" +
                "Call of The Haunted:Activate this card by targeting 1 monster in your GY; Special Summon that target in Attack Position. When this card leaves the field, destroy that monster. When that monster is destroyed, destroy this card.\n" +
                "Change of Heart:Target 1 monster your opponent controls; take control of it until the End Phase.\n" +
                "Closed Forest:All Beast-Type monsters you control gain 100 ATK for each monster in your Graveyard. Field Spell Cards cannot be activated. Field Spell Cards cannot be activated during the turn this card is destroyed.\n" +
                "Command Knight:All Warrior-Type monsters you control gain 400 ATK. If you control another monster, monsters your opponent controls cannot target this card for an attack.\n" +
                "Crab Turtle:This monster can only be Ritual Summoned with the Ritual Spell Card, \"\"Turtle Oath\"\". You must also offer monsters whose total Level Stars equal 8 or more as a Tribute from the field or your hand.\n" +
                "Crawling dragon:This weakened dragon can no longer fly, but is still a deadly force to be reckoned with.\n" +
                "Curtain of the dark ones:A curtain that a spellcaster made, it is said to raise a dark power.\n" +
                "Dark Blade:They say he is a dragon-manipulating warrior from the dark world. His attack is tremendous, using his great swords with vicious power.\n" +
                "Dark Hole:Destroy all monsters on the field.\n" +
                "Dark magician:The ultimate wizard in terms of attack and defense.\n" +
                "Exploder Dragon:If this card is destroyed by battle and sent to the Graveyard: Destroy the monster that destroyed it. Neither player takes any battle damage from attacks involving this attacking card.\n" +
                "Feral Imp:A playful little fiend that lurks in the dark, waiting to attack an unwary enemy.\n" +
                "Fireyarou:A malevolent creature wrapped in flames that attacks enemies with intense fire.\n" +
                "Flame manipulator:This Spellcaster attacks enemies with fire-related spells such as \"\"Sea of Flames\"\" and \"\"Wall of Fire\"\".\n" +
                "Forest:All Insect, Beast, Plant, and Beast-Warrior monsters on the field gain 200 ATK/DEF.\n" +
                "Gate Guardian:Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) by Tributing 1 \"\"Sanga of the Thunder\"\", \"\"Kazejin\"\", and \"\"Suijin\"\".\n" +
                "Haniwa:An earthen figure that protects the tomb of an ancient ruler.\n" +
                "Harpie's Feather Duster:Destroy all Spells and Traps your opponent controls.\n" +
                "Herald of Creation:Once per turn: You can discard 1 card, then target 1 Level 7 or higher monster in your Graveyard; add that target to your hand.\n" +
                "Hero of the east:Feel da strength ah dis sword-swinging samurai from da Far East.\n" +
                "Horn Imp:A small fiend that dwells in the dark, its single horn makes it a formidable opponent.\n" +
                "Leotron:A territorial electronic monster that guards its own domain.\n" +
                "Magic Cylinder:When an opponent's monster declares an attack: Target the attacking monster; negate the attack, and if you do, inflict damage to your opponent equal to its ATK.\n" +
                "Magic Jammer:When a Spell Card is activated: Discard 1 card; negate the activation, and if you do, destroy it.\n" +
                "Magnum Shield:Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position.\n" +
                "● Attack Position: It gains ATK equal to its original DEF.\n" +
                "● Defense Position: It gains DEF equal to its original ATK.\n" +
                "Man-Eater Bug:FLIP: Target 1 monster on the field; destroy that target.\n" +
                "Marshmallon:Cannot be destroyed by battle. After damage calculation, if this card was attacked, and was face-down at the start of the Damage Step: The attacking player takes 1000 damage.\n" +
                "Messenger of peace:Monsters with 1500 or more ATK cannot declare an attack. Once per turn, during your Standby Phase, pay 100 LP or destroy this card.\n" +
                "Mind Crush:Declare 1 card name; if that card is in your opponent's hand, they must discard all copies of it, otherwise you discard 1 random card.\n" +
                "Mirage Dragon:Your opponent cannot activate Trap Cards during the Battle Phase.\n" +
                "Mirror Force:When an opponent's monster declares an attack: Destroy all your opponent's Attack Position monsters.\n" +
                "Monster Reborn:Target 1 monster in either GY; Special Summon it.\n" +
                "Mystical space typhoon:Target 1 Spell/Trap on the field; destroy that target.\n" +
                "Negate Attack:When an opponent's monster declares an attack: Target the attacking monster; negate the attack, then end the Battle Phase.\n" +
                "Pot of Greed:Draw 2 cards.\n" +
                "Raigeki:Destroy all monsters your opponent controls.\n" +
                "Ring of defense:When a Trap effect that inflicts damage is activated: Make that effect damage 0.\n" +
                "Scanner:Once per turn, you can select 1 of your opponent's monsters that is removed from play. Until the End Phase, this card's name is treated as the selected monster's name, and this card has the same Attribute, Level, ATK, and DEF as the selected monster. If this card is removed from the field while this effect is applied, remove it from play.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Skull Guardian:This monster can only be Ritual Summoned with the Ritual Spell Card, \"\"Novox's Prayer\"\". You must also offer monsters whose total Level Stars equal 7 or more as a Tribute from the field or your hand.\n" +
                "Slot Machine:The machine's ability is said to vary according to its slot results.\n" +
                "Solemn Warning:When a monster(s) would be Summoned, OR when a Spell/Trap Card, or monster effect, is activated that includes an effect that Special Summons a monster(s): Pay 2000 LP; negate the Summon or activation, and if you do, destroy it.\n" +
                "Spell Absorption:Each time a Spell Card is activated, gain 500 Life Points immediately after it resolves.\n" +
                "Spiral Serpent:When huge whirlpools lay cities asunder, it is the hunger of this sea serpent at work. No one has ever escaped its dreaded Spiral Wave to accurately describe the terror they experienced.\n" +
                "Suijin:During damage calculation in your opponent's turn, if this card is being attacked: You can target the attacking monster; make that target's ATK 0 during damage calculation only (this is a Quick Effect). This effect can only be used once while this card is face-up on the field.\n" +
                "Supply Squad:Once per turn, if a monster(s) you control is destroyed by battle or card effect: Draw 1 card.\n" +
                "Sword of dark destruction:A DARK monster equipped with this card increases its ATK by 400 points and decreases its DEF by 200 points.\n" +
                "Swords of Revealing Light:After this card's activation, it remains on the field, but destroy it during the End Phase of your opponent's 3rd turn. When this card is activated: If your opponent controls a face-down monster, flip all monsters they control face-up. While this card is face-up on the field, your opponent's monsters cannot declare an attack.\n" +
                "Terraforming:Add 1 Field Spell from your Deck to your hand.\n" +
                "Terratiger, the Empowered Warrior:When this card is Normal Summoned: You can Special Summon 1 Level 4 or lower Normal Monster from your hand in Defense Position.\n" +
                "Texchanger:Once per turn, when your monster is targeted for an attack: You can negate that attack, then Special Summon 1 Cyberse Normal Monster from your hand, Deck, or GY.\n" +
                "The Calculator:The ATK of this card is the combined Levels of all face-up monsters you control x 300.\n" +
                "The Tricky:You can Special Summon this card (from your hand) by discarding 1 card.\n" +
                "Time Seal:Skip the Draw Phase of your opponent's next turn.\n" +
                "Torrential Tribute:When a monster(s) is Summoned: Destroy all monsters on the field.\n" +
                "Trap Hole:When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.\n" +
                "Twin Twisters:Discard 1 card, then target up to 2 Spells/Traps on the field; destroy them.\n" +
                "Umiiruka:Increase the ATK of all WATER monsters by 500 points and decrease their DEF by 400 points.\n" +
                "United We Stand:The equipped monster gains 800 ATK/DEF for each face-up monster you control.\n" +
                "Vanity's Emptiness:Neither player can Special Summon monsters. If a card is sent from the Deck or the field to your Graveyard: Destroy this card.\n" +
                "Wall of Revealing Light:Activate by paying any multiple of 1000 Life Points. Monsters your opponent controls cannot attack if their ATK is less than or equal to the amount you paid.\n" +
                "Warrior Dai Grepher:The warrior who can manipulate dragons. Nobody knows his mysterious past.\n" +
                "Wattaildragon:Capable of indefinite flight. Attacks by wrapping its body with electricity and ramming into opponents.\n" +
                "IMPORTANT: Capturing the \"\"Wattaildragon\"\" is forbidden by the Ancient Rules and is a Level 6 offense, the minimum sentence for which is imprisonment for no less than 2500 heliocycles.\n" +
                "Wattkid:A creature that electrocutes opponents with bolts of lightning.\n" +
                "Yami:All Fiend and Spellcaster monsters on the field gain 200 ATK/DEF, also all Fairy monsters on the field lose 200 ATK/DEF.\n" +
                "Yomi Ship:If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.";
        //Assertions.assertEquals(shopMenuProcessor.process(4, ""), responseFor4);

        responseFor5 = "there is no card with this name";
        Assertions.assertEquals(shopMenuProcessor.process(5,"Not a card"), responseFor5);
        responseFor5 = Card.getCardByName("Axe Raider").getStringForShow();
        Assertions.assertEquals(shopMenuProcessor.process(5,"Axe Raider"), responseFor5);
        responseFor5 = Card.getCardByName("Advanced Ritual Art").getStringForShow();
        Assertions.assertEquals(shopMenuProcessor.process(5,"Advanced Ritual Art"), responseFor5);

        responseFor6 = "100 coins was successfully added to your account, of course by cheats! shame on cheater!";
        Assertions.assertEquals(shopMenuProcessor.process(6, "100"), responseFor6);
        responseFor6 = "invalid value";
        Assertions.assertEquals(shopMenuProcessor.process(6, "100c"), responseFor6);

        responseFor99 = """
                * Commands in this Menu:
                menu enter <name>
                menu exit
                menu show-current
                card show <name>
                shop buy <name>
                shop show --all
                increase --money <amount> (cheat)
                help
                """;
        Assertions.assertEquals(shopMenuProcessor.process(99, ""), responseFor99);
        shopMenuProcessor.enterMenu(Menus.MAIN);
    }

}