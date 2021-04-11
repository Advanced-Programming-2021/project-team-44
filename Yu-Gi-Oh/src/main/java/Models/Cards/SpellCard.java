package Models.Cards;

public class SpellCard extends MagicCard{
    public SpellCard() {
        this.type = "Spell";
    }

    @Override
    public String getStringForShow() {
        return null;
    }
}
