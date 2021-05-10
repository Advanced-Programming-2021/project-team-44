import controller.Core;
import models.cards.MagicCard;
import models.cards.MonsterCard;

public class Main {
    public static void main(String[] args) {
        System.out.println(MonsterCard.monsterCardsJsonParser());
        System.out.println(MagicCard.magicCardsJsonParser());
        Core core = new Core();
        core.run();
    }
}
