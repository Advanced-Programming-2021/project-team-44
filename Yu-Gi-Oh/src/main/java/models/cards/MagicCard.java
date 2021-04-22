package models.cards;

public class MagicCard extends Card{
    protected MagicType type;
    protected MagicIcon icon;

    public MagicCard() {
    }

    public MagicType getType() {
        return type;
    }

    public void setType(MagicType type) {
        this.type = type;
    }

    public MagicIcon getIcon() {
        return icon;
    }

    public void setIcon(MagicIcon icon) {
        this.icon = icon;
    }

    @Override
    public String getStringForShow() {
        String magicCardInfo;
        magicCardInfo = "Name: " + this.getName() + "\n" +
                this.getType() + "\n" +
                "Type: " + this.getIcon() + "\n" +
                "Description: " + this.getDescription();
        return magicCardInfo;
    }
}
