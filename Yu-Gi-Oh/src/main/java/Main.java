import controller.Core;
import models.cards.MagicCard;
import models.cards.MonsterCard;

public class Main {
    public static void main(String[] args) {
        System.out.println(MonsterCard.addMonsterCardFromJSON());
        System.out.println(MagicCard.addMagicCardFromJSON());
        Core core = new Core();
        core.run();
    }
}
